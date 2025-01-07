package com.example.bank.service;

import com.example.bank.rulesdb.jpa.DynamicRuleRepository;
import com.example.bank.rulesdb.DynamicRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DynamicRuleServiceTest {

    @Mock
    private DynamicRuleRepository dynamicRuleRepository;

    @InjectMocks
    private DynamicRuleService dynamicRuleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateRule() {
        DynamicRule rule = new DynamicRule();
        rule.setProductName("Test Product");

        when(dynamicRuleRepository.save(rule)).thenReturn(rule);

        DynamicRule createdRule = dynamicRuleService.createRule(rule);

        assertNotNull(createdRule);
        assertEquals("Test Product", createdRule.getProductName());
        verify(dynamicRuleRepository, times(1)).save(rule);
    }

    @Test
    void testGetAllRules() {
        List<DynamicRule> rules = new ArrayList<>();
        rules.add(new DynamicRule());

        when(dynamicRuleRepository.findAll()).thenReturn(rules);

        List<DynamicRule> result = dynamicRuleService.getAllRules();

        assertEquals(1, result.size());
    }
}
