package com.hello;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;


import lombok.Data;

@MappedSuperclass
@Data
public class BaseModel implements Serializable{
	


    private static final long serialVersionUID = 1302906834702498566L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createTime", nullable = false)
    private Date createTime;

    /**
     * 修改时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updateTime", nullable = false)
    private Date updateTime;

    /**
     * 版本号
     */
    @Version
    @Column(name = "version", nullable = false)
    private Integer version;


	

}
