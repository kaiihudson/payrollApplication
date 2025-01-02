package payroll.order.service;

import org.springframework.stereotype.Service;
import payroll.order.controller.OrderNotFoundException;
import payroll.order.model.AppOrder;

import payroll.order.model.OrderStatus;
import payroll.order.repository.OrderRepository;
import payroll.person.model.Person;
import payroll.person.repository.PeopleRepository;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Order service.
 */
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final AppOrderMapper appOrderMapper;
    private final PeopleRepository peopleRepository;

    /**
     * Instantiates a new Order service.
     *
     * @param orderRepository  the order repository
     * @param appOrderMapper   the app order mapper
     * @param peopleRepository the people repository
     */
    OrderService(OrderRepository orderRepository, AppOrderMapper appOrderMapper, PeopleRepository peopleRepository) {
        this.orderRepository = orderRepository;
        this.appOrderMapper = appOrderMapper;
        this.peopleRepository = peopleRepository;
    }

    /**
     * Gets all orders.
     *
     * @return the all orders
     */
    public List<AppOrderDTO> getAllOrders() {
        List<AppOrder> orders = orderRepository.findAll();
        return orders.stream() //
                .map(appOrderMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Gets order by id.
     *
     * @param id the id
     * @return the order by id
     */
    public AppOrderDTO getOrderById(Long id) {
        AppOrder appOrder = orderRepository.findById(id) //
                .orElseThrow(() -> new OrderNotFoundException(id));
        return appOrderMapper.mapToDTO(appOrder);
    }

    /**
     * Gets raw order by id.
     *
     * @param id the id
     * @return the raw order by id
     */
    public AppOrder getRawOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    /**
     * Gets incomplete order by user id.
     *
     * @param person_id the person id
     * @return the incomplete order by user id
     */
    public List<AppOrderDTO> getIncompleteOrderByUserId(Long person_id) {
        Optional<Person> person = peopleRepository.findById(person_id);
        if (person.isPresent()) {
            List<AppOrder> orders = orderRepository.findByBindUserAndOrderStatusNotOrderByRetailerAsc(
                    person.get(),
                    OrderStatus.COMPLETE
            );
            return orders.stream()
                    .map(appOrderMapper::mapToDTO)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Gets complete order by user id.
     *
     * @param person_id the person id
     * @return the complete order by user id
     */
    public List<AppOrderDTO> getCompleteOrderByUserId(Long person_id) {
        Optional<Person> person = peopleRepository.findById(person_id);
        if(person.isPresent()) {
            List<AppOrder> orders = orderRepository.findByBindUserAndOrderStatus(
                    person.get(),
                    OrderStatus.COMPLETE
            );
            return orders.stream()
                    .map(appOrderMapper::mapToDTO)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }
    private AppOrder addPersonToOrder(AppOrder order, Person person) {
        return new AppOrder.Builder()
                .orderStatus(OrderStatus.CREATED)
                .creationDate(new Date())
                .bindUser(person)
                .build();
    }

    /**
     * Create order app order dto.
     *
     * @param order  the order
     * @param person the person
     * @return the app order dto
     */
    public AppOrderDTO createOrder(AppOrder order, Person person) {
        AppOrder savedOrder = orderRepository.save(this.addPersonToOrder(order, person));
        return appOrderMapper.mapToDTO(savedOrder);
    }

    /**
     * Update order by id app order dto.
     *
     * @param id       the id
     * @param newOrder the new order
     * @return the app order dto
     */
    public AppOrderDTO updateOrderById(Long id, AppOrder newOrder) {
        Optional<AppOrder> originalOrder = orderRepository.findById(id);
        if(originalOrder.isPresent()) {
            originalOrder
                    .map(order -> {
                        order.setOrderStatus(newOrder.getOrderStatus());
                        order.setExecutionDate(newOrder.getExecutionDate());
                        return orderRepository.save(order);
                    });
        }
        return null;
    }
}
