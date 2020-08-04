<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${mapperClass.packageName}.${mapperClass.className}">

    <!-- 插入数据 -->
    <insert id="insert" parameterType="${mapperClass.po.packageName}.${mapperClass.po.className}" useGeneratedKeys="true" keyProperty="id">
        INSERT INTO ${mapperClass.po.table.name}
        <trim prefix="(" suffix=")" suffixOverrides=",">
        <#list mapperClass.po.table.columns as column>
            <if test="${column.name} != null">
                ${column.name},
            </if>
		</#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
        <#list mapperClass.po.table.columns as column>
            <if test="${column.name} != null">
                #${r'{'}${column.name}},
            </if>
		</#list>
        </trim>
    </insert>

    <!-- 更新数据 -->
    <update id="update" parameterType="${mapperClass.po.packageName}.${mapperClass.po.className}">
        UPDATE ${mapperClass.po.table.name}
        <set>
            <#list mapperClass.po.table.columns as column>
                <#if column.isPrimaryKey == false>
            <if test="${column.name} != null">
                ${column.name} = #${r'{'}${column.name}},
            </if>
                </#if>
            </#list>
        </set>
        <where>
        <#list mapperClass.po.table.columns as column>
        <#if column.name == "version">
            <if test="${column.name} != null">
                AND ${column.name} = #${r'{'}${column.name}} - 1
            </if>
        <#else>
            <if test="${column.name} != null">
                AND ${column.name} = #${r'{'}${column.name}}
            </if>
        </#if>
        </#list>
        </where>
    </update>

    <!-- 所有列 -->
    <sql id="ALL_COLUMNS">
        <#list mapperClass.po.table.columns as column>
            ${column.name}<#if column_has_next>,</#if>
        </#list>
    </sql>

    <!-- 根据相等条件查询数据 -->
    <select id="selectByEQ" parameterType="${mapperClass.po.packageName}.${mapperClass.po.className}">
        SELECT
        <include refid="ALL_COLUMNS" />
        FROM ${mapperClass.po.table.name}
        <where>
        <#list mapperClass.po.table.columns as column>
            <if test="${column.name} != null">
                AND ${column.name} = #${r'{'}${column.name}}
            </if>
	    </#list>
        </where>
    </select>

    <!-- 分页搜索条件 -->
    <sql id="selectPageListWhere">
        <where>
        <#list mapperClass.po.table.columns as column>
            <if test="${column.name} != null">
                AND ${column.name} = #${r'{'}${column.name}}
            </if>
        </#list>
        </where>
    </sql>

    <!-- 查询列表总数 -->
    <select id="selectCountPageList" resultType="int">
        SELECT COUNT(1)
        FROM ${mapperClass.po.table.name}
        <include refid="selectPageListWhere" />
    </select>

    <!-- 查询列表(分页) -->
    <select id="selectPageList" resultType="${mapperClass.po.packageName}.${mapperClass.po.className}">
        SELECT
        <include refid="ALL_COLUMNS" />
        FROM ${mapperClass.po.table.name}
        <include refid="selectPageListWhere" />
        ORDER BY createTime DESC
        LIMIT #${r'{'}pageCursor} , #${r'{'}pageSize}
    </select>

</mapper>