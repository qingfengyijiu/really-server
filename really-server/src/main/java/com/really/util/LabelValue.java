/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package com.really.util;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zhangjx.wx.persistence.BaseDomain;

/**
 * @since 1.0 2013-3-12,下午1:30:09
 * 数据字典
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name="T_UTILS_LABEL_VALUE")
@NamedQueries(value={@NamedQuery(name="LabelValue.findByValue",query="select lv from LabelValue lv where lv.value = :value")})
public class LabelValue extends BaseDomain<Long> {

	private static final long serialVersionUID = -8974485587479952306L;
	
	@Id@Column(name="PK")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	//标签
	@Column(name="LABLE",length=10)
	private String label;
	
	//值
	@Column(name="VALUE", length=50)
	private String value;
	
	//父节点
	@ManyToOne(fetch=FetchType.LAZY,optional=true,targetEntity=LabelValue.class)
	@JoinColumn(name="PARENT_PK",nullable=true,unique=false)
	@JsonIgnore
	private LabelValue parent;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="parent",targetEntity=LabelValue.class,cascade=CascadeType.ALL)
	//@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@OrderBy("order ASC")
	private List<LabelValue> children;
	
	//顺序
	@Column(name="L_ORDER")
	private int order = 0;
	
	//描述
	@Column(name="L_ADD", length=40)
	private String addition;
	
	/**
	 * 
	 */
	public LabelValue() {
		super();
	}

	/**
	 * @param label
	 * @param value
	 */
	public LabelValue(String label, String value) {
		this.label = label;
		this.value = value;
	}
	
	/**
     * @param label
     * @param value
     * @param addition
     */
    public LabelValue(String label, String value, String addition) {
        this(label, value);
        this.addition = addition;
    }

    /**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the parent
	 */
	public LabelValue getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(LabelValue parent) {
		this.parent = parent;
	}

	/**
	 * @return the children
	 */
	public List<LabelValue> getChildren() {
		return children;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(List<LabelValue> children) {
		this.children = children;
	}

	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}

    /**
     * @return the addition
     */
    public String getAddition() {
        return addition;
    }

    /**
     * @param addition the addition to set
     */
    public void setAddition(String addition) {
        this.addition = addition;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((addition == null) ? 0 : addition.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((label == null) ? 0 : label.hashCode());
        result = prime * result + order;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        LabelValue other = (LabelValue) obj;
        if(addition == null) {
            if(other.addition != null)
                return false;
        } else if(!addition.equals(other.addition))
            return false;
        if(id == null) {
            if(other.id != null)
                return false;
        } else if(!id.equals(other.id))
            return false;
        if(label == null) {
            if(other.label != null)
                return false;
        } else if(!label.equals(other.label))
            return false;
        if(order != other.order)
            return false;
        if(value == null) {
            if(other.value != null)
                return false;
        } else if(!value.equals(other.value))
            return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "LabelValue [id=" + id + ", label=" + label + ", value=" + value + ", order=" + order + ", addition=" + addition + "]";
    }

	
	
}
