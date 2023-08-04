package com.fastkart.ecomm.FastKartEcomm.service;

import com.fastkart.ecomm.FastKartEcomm.dto.BidRequest;
import org.springframework.stereotype.Service;

@Service
public interface BidService {
    public String submitBit(BidRequest request);
}
