package com.heitian.ssm.arch.base;

import lombok.Data;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Date;

/**
 * 所有领域模型的基类
 * Created by walden on 2016/11/2.
 */
@Data
public class BaseDomain implements Serializable {
    /**
     * 主键
     */
    private String id;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * false 有效 true 已删除
     */
    private Integer status;

}
