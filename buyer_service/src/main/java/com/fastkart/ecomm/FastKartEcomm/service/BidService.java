package com.fastkart.ecomm.FastKartEcomm.service;

import com.fastkart.ecomm.FastKartEcomm.dto.BidRequest;
import com.fastkart.ecomm.FastKartEcomm.entity.Bid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BidService {
    public String submitBit(BidRequest request);
}
