//package org.example;
//
//import com.alibaba.csp.sentinel.Entry;
//import com.alibaba.csp.sentinel.SphU;
//import com.alibaba.csp.sentinel.slots.block.BlockException;
//import com.alibaba.csp.sentinel.slots.block.RuleConstant;
//import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
//import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author Jdx
// * @version 1.0
// * @description
// * @date 2021/2/25 11:49
// */
//public class SentinalTest {
//    public static void main(String[] args) {
//        // 配置规则.
//        initFlowRules();
//        while (true) {
//            // 1.5.0 版本开始可以直接利用 try-with-resources 特性
//            try (Entry entry = SphU.entry("HelloWorld")) {
//                // 被保护的逻辑
//                System.out.println("hello world");
//            } catch (BlockException ex) {
//                // 处理被流控的逻辑
//                System.out.println("blocked!");
//            }
//        }
//    }
//    private static void initFlowRules(){
//        List<FlowRule> rules = new ArrayList<>();
//        FlowRule rule = new FlowRule();
//        rule.setResource("HelloWorld");
//        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
//        // Set limit QPS to 10  每秒最多能通过10个请求
//        rule.setCount(10);
//        rules.add(rule);
//        FlowRuleManager.loadRules(rules);
//    }
//}
