package payroll.order.service;

import org.springframework.stereotype.Service;
import payroll.order.controller.OrderNotFoundException;
import payroll.order.model.AppOrder;

import payroll.order.repository.OrderRepository;
import payroll.person.model.Person;
import payroll.person.repository.PeopleRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final AppOrderMapper appOrderMapper;
    private final PeopleRepository peopleRepository;

    OrderService(OrderRepository orderRepository, AppOrderMapper appOrderMapper, PeopleRepository peopleRepository) {
        this.orderRepository = orderRepository;
        this.appOrderMapper = appOrderMapper;
        this.peopleRepository = peopleRepository;
    }

    public List<AppOrderDTO> getAllOrders() {
        List<AppOrder> orders = orderRepository.findAll();
        return orders.stream() //
                .map(appOrderMapper::mapToDTO)
                .collect(Collectors.toList());
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

    public List<AppOrderDTO> getOrderByUserId(Long person_id) {
        Optional<Person> person = peopleRepository.findById(person_id);
        if (person.isPresent()) {
            List<AppOrder> orders = orderRepository.findByBindUser(person.get());
            return orders.stream()
                    .map(appOrderMapper::mapToDTO)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    public AppOrderDTO createOrder(AppOrder order) {
        AppOrder savedOrder = orderRepository.save(order);
        return appOrderMapper.mapToDTO(savedOrder);
    }
}
