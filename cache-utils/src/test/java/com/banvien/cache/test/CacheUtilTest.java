
/**
 * Created by Hieu Le on 9/26/2016.
 */

package com.banvien.cache.test;

import com.karlchu.cache.utils.CacheUtil;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.io.Serializable;

public class CacheUtilTest  extends TestCase implements Serializable {
  private static final long serialVersionUID = 3450942483541244398L;

  @Test
  public void testPutAndGetValue() throws InterruptedException {
    String key = "test1";
    TestData testData = new TestData("TestCase", 100);
    CacheUtil.getInstance().putValue(key, testData);
    Thread.sleep(10);
    Assert.assertTrue(CacheUtil.getInstance().getValue(key).equals(testData));
  }

  @Test
  public void testRemove() throws InterruptedException {
    String key = "test2";
    TestData testData = new TestData("TestCase", 100);
    CacheUtil.getInstance().putValue(key, testData);

    Thread.sleep(10);

    Assert.assertTrue(CacheUtil.getInstance().isExists(key));
    CacheUtil.getInstance().remove(key);

    Assert.assertTrue( !CacheUtil.getInstance().isExists(key));
  }

  @Test
  public void testClearAll() throws InterruptedException {
    String key = "test3";
    TestData testData = new TestData("TestCase", 100);
    CacheUtil.getInstance().putValue(key, testData);

    Thread.sleep(10);

    Assert.assertTrue(CacheUtil.getInstance().isExists(key));
    CacheUtil.getInstance().clearAll();

    Assert.assertTrue( !CacheUtil.getInstance().isExists(key));
  }

  private class TestData implements Serializable {

    private static final long serialVersionUID = -3748108790010095853L;
    private String name;
    private Integer age;

    public TestData(String name, Integer age) {
      this.name = name;
      this.age = age;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public Integer getAge() {
      return age;
    }

    public void setAge(Integer age) {
      this.age = age;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      TestData testData = (TestData) o;

      if (name != null ? !name.equals(testData.name) : testData.name != null) return false;
      return age != null ? age.equals(testData.age) : testData.age == null;

    }

    @Override
    public int hashCode() {
      int result = name != null ? name.hashCode() : 0;
      result = 31 * result + (age != null ? age.hashCode() : 0);
      return result;
    }
  }
}
