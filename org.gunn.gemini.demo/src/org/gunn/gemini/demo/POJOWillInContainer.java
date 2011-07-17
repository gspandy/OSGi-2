package org.gunn.gemini.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * This pojo will create by blueprint container
 * @author 
 *
 */
public class POJOWillInContainer {
    private Logger logger = LoggerFactory.getLogger(POJOWillInContainer.class);
    
    private String name ;
    
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        logger.info("the new name is:" + name);
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        logger.info("the new age value is:" + age);
        this.age = age;
    }

	public String toString() {
		return "POJOWillInContainer [logger=" + logger + ", name=" + name
				+ ", age=" + age + "]";
	}
    
    
}
