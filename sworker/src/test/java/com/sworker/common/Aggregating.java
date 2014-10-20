package com.sworker.common;

import com.sworker.dao.BlogDaoImplTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 用于集成测试，各个测试单元可以如下
 * Created by zhangjin on 2014/8/26.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
      BlogDaoImplTests.class
})
public class Aggregating {
    // the class remains empty,
    // used only as a holder for the above annotations
}
