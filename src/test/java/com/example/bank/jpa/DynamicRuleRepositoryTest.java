package com.example.bank.jpa;

import com.example.bank.rulesdb.DynamicRule;
import com.example.bank.rulesdb.jpa.DynamicRuleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test") // Активируем тестовый профиль
@Import(DynamicRuleRepository.class) // Импортируем репозиторий
class DynamicRuleRepositoryTest {

    @Autowired
    private DynamicRuleRepository dynamicRuleRepository;

    @Test
    void testSaveAndFindAll() {
        // Создаем объект DynamicRule
        DynamicRule rule = new DynamicRule();
        rule.setProductName("Test Product");
        rule.setProductId("12345");
        rule.setProductText("Test Text");
        rule.setRuleJson("{\"key\":\"value\"}");

        // Сохраняем объект
        DynamicRule savedRule = dynamicRuleRepository.save(rule);

        // Проверяем, что объект сохранен
        assertNotNull(savedRule.getId(), "ID не должен быть null");

        // Проверяем данные в базе
        List<DynamicRule> rules = dynamicRuleRepository.findAll();
        assertEquals(1, rules.size(), "В базе должен быть 1 объект");
        assertEquals("Test Product", rules.get(0).getProductName());
        assertEquals("12345", rules.get(0).getProductId());
        assertEquals("Test Text", rules.get(0).getProductText());
        assertEquals("{\"key\":\"value\"}", rules.get(0).getRuleJson());
    }
}
