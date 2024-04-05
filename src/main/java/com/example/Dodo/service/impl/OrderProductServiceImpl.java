package com.example.Dodo.service.impl;

import com.example.Dodo.base.BaseServiceImpl;
import com.example.Dodo.dao.OrderProductRepository;
import com.example.Dodo.mapper.OrderProductMapper;
import com.example.Dodo.model.DTO.*;
import com.example.Dodo.model.entity.OrderProduct;
import com.example.Dodo.model.enums.OrderStatus;
import com.example.Dodo.model.request.OrderCreateRequest;
import com.example.Dodo.model.request.OrderRepeatRequest;
import com.example.Dodo.model.request.ProductListForRepeat;
import com.example.Dodo.model.request.ProductOrderList;
import com.example.Dodo.model.response.OrderHistoryResponse;
import com.example.Dodo.model.response.OrderListResponse;
import com.example.Dodo.model.response.ProductHistoryResponse;
import com.example.Dodo.model.response.ProductListResponse;
import com.example.Dodo.service.*;
import com.example.Dodo.util.JwtProvider;
import com.example.Dodo.util.Language;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderProductServiceImpl extends BaseServiceImpl<OrderProduct, OrderProductRepository, OrderProductDTO, OrderProductMapper> implements OrderProductService {
    public OrderProductServiceImpl(OrderProductRepository rep, OrderProductMapper mapper, JwtProvider jwtProvider, UserService userService, OrderService orderService, ProductSizeService productSizeService, AddressService addressService) {
        super(rep, mapper);
        this.jwtProvider = jwtProvider;
        this.userService = userService;
        this.orderService = orderService;
        this.productSizeService = productSizeService;
        this.addressService = addressService;
    }

    private final JwtProvider jwtProvider;
    private final UserService userService;
    private final OrderService orderService;
    private final ProductSizeService productSizeService;
    private final AddressService addressService;

    @Override
    public String create(OrderCreateRequest request, Integer languageOrdinal, String accessToken) {

        Language language = Language.getLanguage(languageOrdinal);

        UserDTO user = userService.findById(jwtProvider.validateToken(accessToken, languageOrdinal));
        AddressDTO address = addressService.findById(request.getAddressId());

        OrderDTO order = new OrderDTO();
        order.setOrderDate(request.getOrderDate());
        order.setPaymentType(request.getPaymentType());
        order.setAddress(address);
        order.setUser(user);

        order = orderService.save(order);

        BigDecimal price = BigDecimal.ZERO;
        Integer dodocoinsPayed = 0;
        BigDecimal discount = BigDecimal.ZERO;

        for(ProductOrderList i: request.getProductOrderLists()) {
            ProductSizeDTO productSize = productSizeService.findById(i.getId());

            if(i.getPrice() == null || i.getPrice().equals(BigDecimal.ZERO)) {
                discount = discount.add(productSize.getPrice());

                BigDecimal percent = (productSize.getPrice().divide(BigDecimal.valueOf(100))).multiply(BigDecimal.valueOf(10));
                percent = percent.setScale(0);
                Integer intPercent = percent.intValue();

                dodocoinsPayed += intPercent;
            }

            price = price.add(i.getPrice());
            OrderProductDTO orderProduct = new OrderProductDTO();
            orderProduct.setOrder(order);
            orderProduct.setProductSize(productSize);

            save(orderProduct);
        }

        order.setOrderStatus(OrderStatus.NEW);
        order.setDodocoins(dodocoinsPayed);
        order.setPrice(price);
        order.setDiscount(discount);
        order = orderService.update(order);

        BigDecimal result = price.divide(BigDecimal.valueOf(20), 10, BigDecimal.ROUND_HALF_UP);
        Integer dodocoinsJoin = result.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();

        user.setDodocoins(dodocoinsJoin);
        userService.update(user);


        return "Success";
    }

    @Override
    public List<OrderProductDTO> getByOrderId(Long orderId) {
        return mapper.toDtos(rep.findByOrderId(orderId), context);
    }


    @Override
    public OrderCreateRequest repeatOrder(Long orderId, String accessToken, Integer languageOrdinal) {

        List<ProductListForRepeat> productList = rep.findProductsForRepeat(orderId);
        List<ProductOrderList> newList = new ArrayList<>();
        OrderCreateRequest orderCreateRequest = new OrderCreateRequest();
        for(ProductListForRepeat i: productList) {
            ProductOrderList productOrderList = new ProductOrderList();
            productOrderList.setId(i.getId());
            productOrderList.setPrice(i.getPrice());
            newList.add(productOrderList);

            orderCreateRequest.setOrderDate(i.getOrderDate());
            orderCreateRequest.setAddressId(i.getAddressId());
            orderCreateRequest.setPaymentType(i.getPaymentType());
        }
        orderCreateRequest.setProductOrderLists(newList);

        return orderCreateRequest;
    }

    @Override
    public List<ProductListResponse> historyOfProductsByOrder(Long orderId) {
        return rep.historyOfProductsByOrder(orderId);
    }

    @Override
    public Page<OrderHistoryResponse> historyOfOrders(String accessToken, Integer languageOrdinal, Integer limit) {

        Long userId = jwtProvider.validateToken(accessToken, languageOrdinal);
        Pageable page = PageRequest.of(0, limit);

        List<OrderListResponse> orderListResponse = rep.historyOfOrders(userId);
        List<OrderHistoryResponse> orderHistoryResponseList = new ArrayList<>();

        for (OrderListResponse i: orderListResponse) {
            List<ProductHistoryResponse> productsResponse = new ArrayList<>();

            OrderHistoryResponse orderHistoryResponse = new OrderHistoryResponse();
            orderHistoryResponse.setId(i.getId());
            orderHistoryResponse.setOrderDate(i.getOrderDate());
            orderHistoryResponse.setPrice(i.getPrice());
            orderHistoryResponse.setAddress(i.getAddress());

            List<ProductListResponse> productListResponse = rep.historyOfProductsByOrder(i.getId());
            for (ProductListResponse item: productListResponse) {

                ProductHistoryResponse productHistoryResponse = new ProductHistoryResponse();
                productHistoryResponse.setProductId(item.getProductId());
                productHistoryResponse.setName(item.getName());
                productHistoryResponse.setSize(item.getSize());

                productsResponse.add(productHistoryResponse);
            }

            orderHistoryResponse.setList(productsResponse);
            orderHistoryResponseList.add(orderHistoryResponse);
        }
        Page<OrderHistoryResponse> orderPage = new PageImpl<>(orderHistoryResponseList, page, orderHistoryResponseList.size());

        return orderPage;
    }
}
