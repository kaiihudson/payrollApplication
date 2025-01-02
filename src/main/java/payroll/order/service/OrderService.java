package payroll.order.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import payroll.items.repository.OrderItemRepository;
import payroll.order.exception.OrderIsInInvalidStateException;
import payroll.order.exception.OrderNotAbortedException;
import payroll.order.exception.OrderNotFoundException;
import payroll.order.model.AppOrder;

import payroll.order.model.OrderStatus;
import payroll.order.model.OrderUpdate;
import payroll.order.repository.OrderRepository;
import payroll.person.model.Person;
import payroll.person.repository.PeopleRepository;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final AppOrderMapper appOrderMapper;
    private final PeopleRepository peopleRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    OrderService(
            OrderRepository orderRepository,
            AppOrderMapper appOrderMapper,
            PeopleRepository peopleRepository,
            OrderItemRepository orderItemRepository
    ) {
        this.orderRepository = orderRepository;
        this.appOrderMapper = appOrderMapper;
        this.peopleRepository = peopleRepository;
        this.orderItemRepository = orderItemRepository;
    }

    private final List<OrderStatus> doneStatement = Arrays.asList(OrderStatus.COMPLETE, OrderStatus.ABORTED);
    private Float totalOfOrder (AppOrder order) {
        Float total = orderItemRepository.sumTotalPriceByOrderId(order);
        if(total == null){
            total = 0.0F;
        }
        return total;

    }

    public List<AppOrderDTO> getAllOrders() {
        List<AppOrder> orders = orderRepository.findByOrderStatusNotInOrderByOrderStatus(doneStatement);
        return orders.stream() //
                .map(appOrderMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<AppOrderDTO> getAllOrdersWithTotals() {
        List<AppOrder> orders = orderRepository.findByOrderStatusNotInOrderByOrderStatus(doneStatement);
        List<AppOrderDTO> responseOrders = new ArrayList<>();
        for (AppOrder order : orders) {
            AppOrderDTO orderDTO = appOrderMapper.mapToDTOWithTotal(order, totalOfOrder(order));
            responseOrders.add(orderDTO);
        }
        return new ArrayList<>(responseOrders);
    }

    public AppOrderDTO getOrderById(Long id) {
        AppOrder appOrder = orderRepository.findById(id) //
                .orElseThrow(() -> new OrderNotFoundException(id));
        return appOrderMapper.mapToDTO(appOrder);
    }

    public AppOrder getRawOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    public List<AppOrderDTO> getIncompleteOrderByUserId(Long person_id) {
        Optional<Person> person = peopleRepository.findById(person_id);
        if (person.isPresent()) {
            List<AppOrder> orders = orderRepository.findByBindUserAndOrderStatusNotInOrderByRetailerAsc(
                    person.get(),
                    doneStatement
            );

            List<AppOrderDTO> returnOrders = new ArrayList<>();
            for (AppOrder order: orders) {
                AppOrderDTO orderWithTotal = appOrderMapper.mapToDTOWithTotal(order, totalOfOrder(order));
                returnOrders.add(orderWithTotal);
            }
            return returnOrders;
        } else {
            return new ArrayList<>();
        }
    }


    public Page<EntityModel<AppOrderDTO>> getPreviousOrderByUserId(Long person_id, Pageable p) {
        Optional<Person> person = peopleRepository.findById(person_id);
        if(person.isPresent()) {
            Page<AppOrder> pageOfOrder = orderRepository.findByBindUserAndOrderStatusInOrderByOrderStatusAsc(
                    person.get(),
                    doneStatement,
                    p
            );
            return new PageImpl<>(pageOfOrder.stream()
                    .map(appOrderMapper::mapToDTO)
                    .map(appOrderMapper::mapToEntityModel)
                    .collect(Collectors.toList()),
                    p,
                    pageOfOrder.getTotalElements());
        } else {
            return null;
        }
    }
    private AppOrder addPersonToOrder(AppOrder order, Person person) {
        return new AppOrder.Builder()
                .orderStatus(OrderStatus.CREATED)
                .creationDate(new Date())
                .bindUser(person)
                .retailer(order.getRetailer())
                .build();
    }

    public AppOrderDTO createOrder(AppOrder order, Person person) {
        AppOrder savedOrder = orderRepository.save(this.addPersonToOrder(order, person));
        return appOrderMapper.mapToDTO(savedOrder);
    }

    public AppOrderDTO updateOrderById(Long id, AppOrder newOrder) {
        Optional<AppOrder> originalOrder = orderRepository.findById(id);
        if(originalOrder.isPresent()) {
            originalOrder
                    .map(order -> {
                        order.setOrderStatus(newOrder.getOrderStatus());
                        order.setExecutionDate(new Date());
                        return orderRepository.save(order);
                    });
        }
        return null;
    }

    public AppOrderDTO deleteOrderById (Long id) {
        AppOrder order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        if (order.getOrderStatus() != OrderStatus.ABORTED) {
            order.setOrderStatus(OrderStatus.ABORTED);
            order.setExecutionDate(new Date());
            orderRepository.save(order);
        } else{
            throw(new OrderNotAbortedException(id));
        }
        return null;
    }
    private OrderStatus selectNextStatus(AppOrder order) {
        OrderStatus currentStatus = order.getOrderStatus();
        switch (currentStatus){
            case CREATED -> {
                return OrderStatus.IN_PROGRESS;
            }
            case IN_PROGRESS -> {
                return OrderStatus.RECEIVED;
            }
            case RECEIVED -> {
                return OrderStatus.COMPLETE;
            }
            case COMPLETE -> {
                throw new OrderIsInInvalidStateException(OrderStatus.COMPLETE);
            }
            case ABORTED -> {
                throw new OrderIsInInvalidStateException(OrderStatus.ABORTED);
            }
        }
        return null;
    }

    public void updateList(OrderUpdate orderUpdate) {
        for(int i = 0; i < orderUpdate.getIdList().size(); i++ ) {
          Long id = orderUpdate.getIdList().get(i);
          Optional<AppOrder> order = orderRepository.findById(id);
          if (order.isPresent()) {
              AppOrder editedOrder = order.get();
              editedOrder.setOrderStatus(selectNextStatus(order.get()));
              editedOrder.setExecutionDate(new Date());
              orderRepository.save(editedOrder);
          } else {
              throw(new OrderNotFoundException(id));
          }
        }
    }
}
