package com.example.warehouse.dealCach;

import com.example.warehouse.models.DealModel;

import java.util.LinkedHashMap;
import java.util.Map;

public class DealCache {
    private static final int MAX_ENTRIES = 1000; // maximum number of entries in the cache

    private final LinkedHashMap<Long, DealModel> dealCache = new LinkedHashMap<Long, DealModel>(MAX_ENTRIES + 1, 0.75f, true) {
        @Override
        protected boolean removeEldestEntry(Map.Entry<Long, DealModel> eldest) {
            return size() > MAX_ENTRIES;
        }
    };

    public boolean addDealToCache(DealModel deal) {
        if (dealCache.containsKey(deal.getId())) {
            return false; // Deal with this ID already exists
        }
        dealCache.put(deal.getId(), deal);
        return true;
    }

    public DealModel getDeal(Long id) {
        return dealCache.get(id);
    }

    public boolean containsDeal(Long id) {
        return dealCache.containsKey(id);
    }
}
