package com.springcommerce.controller;

import com.springcommerce.model.Order;
import com.springcommerce.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    public void getOrderById_ReturnsOrderWithGivenId() throws Exception {
        int id = 1;
        Order order = new Order();
        order.setId(id);
        Mockito.when(orderService.findById(id)).thenReturn(order);

        mockMvc.perform(MockMvcRequestBuilders.get("/orders/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{\"id\":1}"));
    }

    @Test
    public void createOrder_ReturnsCreatedOrder() throws Exception {
        Order order = new Order();
        order.setId(1);
        Mockito.when(orderService.save(Mockito.any(Order.class))).thenReturn(order);

        mockMvc.perform(MockMvcRequestBuilders.post("/orders/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json("{\"id\":1}"));
    }

    @Test
    public void deleteOrderById_DeletesOrderWithGivenId() throws Exception {
        int id = 1;

        mockMvc.perform(MockMvcRequestBuilders.delete("/orders/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(orderService, Mockito.times(1)).deleteById(id);
    }

    @Test
    public void getAllOrders_ReturnsAllOrders() throws Exception {
        Order order1 = new Order();
        order1.setId(1);
        Order order2 = new Order();
        order2.setId(2);
        List<Order> orders = Arrays.asList(order1, order2);

        Mockito.when(orderService.getAllOrders()).thenReturn(orders);

        mockMvc.perform(MockMvcRequestBuilders.get("/orders/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("orders", orders))
                .andExpect(MockMvcResultMatchers.view().name("order-list"));
    }

    @Test
    public void updateOrder_UpdatesOrderWithGivenId() throws Exception {
        int id = 1;
        Order order = new Order();
        order.setId(id);
        Order updatedOrder = new Order();
        updatedOrder.setId(id + 1);

        Mockito.when(orderService.updateOrder(Mockito.any(Order.class))).thenReturn(updatedOrder);

        mockMvc.perform(MockMvcRequestBuilders.put("/orders/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{\"id\":2}"));
    }
}
