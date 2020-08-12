<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${mapperClass.packageName}.${mapperClass.className}">

    <!-- 插入数据 -->
    <insert id="insert" parameterType="${mapperClass.po.packageName}.${mapperClass.po.className}">
        <include refid="insertSQL" />
    </insert>

    <!-- 插入批量数据 -->
    <insert id="insertBatch">
        INSERT INTO ${mapperClass.po.table.name}
            <trim prefix="(" suffix=")" suffixOverrides=",">
            <#list mapperClass.po.table.columns as column>
            ${column.name},
		    </#list>
            </trim>
        VALUES
        <foreach collection="list" item="item" index="index" separator="," >
            <trim prefix="(" suffix=")" suffixOverrides=",">
            <#list mapperClass.po.table.columns as column>
            #${r'{'}item.${column.name}},
		    </#list>
            </trim>
        </foreach>
    </insert>

    <!-- 插入或更新数据 -->
    <insert id="insertOrUpdate" parameterType="${mapperClass.po.packageName}.${mapperClass.po.className}">
        <selectKey keyProperty="count" resultType="int" order="BEFORE">
            SELECT COUNT(*)
            FROM ${mapperClass.po.table.name}
            <#list mapperClass.po.table.columns as column>
                <#if column.isPrimaryKey == true>
            WHERE ${column.name} = #${r'{'}${column.name}}
                </#if>
            </#list>
        </selectKey>
        <if test="count == 0">
            <include refid="insertSQL" />
        </if>
        <if test="count > 0">
            UPDATE ${mapperClass.po.table.name}
            <set>
            <#list mapperClass.po.table.columns as column>
            <#if column.isPrimaryKey == false && column.name != 'createTime'>
            <if test="${column.name} != null">
                ${column.name} = #${r'{'}${column.name}},
            </if>
            </#if>
            </#list>
        </set>
            <#list mapperClass.po.table.columns as column>
                <#if column.isPrimaryKey == true>
            WHERE ${column.name} = #${r'{'}${column.name}}
                </#if>
            </#list>
        </if>
    </insert>

    <!-- 更新数据 -->
    <update id="update">
        UPDATE ${mapperClass.po.table.name}
        <set>
            <#list mapperClass.po.table.columns as column>
            <#if column.isPrimaryKey == false && column.name != 'createTime'>
            <if test="${column.name} != null">
                ${column.name} = #${r'{'}uw.${column.name}},
            </if>
            </#if>
            </#list>
        </set>
        <include refid="queryCondition" />
    </update>

    <!-- 查询一条记录 -->
    <select id="selectOne" resultType="${mapperClass.po.packageName}.${mapperClass.po.className}">
        SELECT
        <include refid="ALL_COLUMNS" />
        FROM ${mapperClass.po.table.name}
        <include refid="queryCondition" />
        LIMIT 1
    </select>

    <!-- 查询所有记录 -->
    <select id="selectList" resultType="${mapperClass.po.packageName}.${mapperClass.po.className}">
        SELECT
        <include refid="ALL_COLUMNS" />
        FROM ${mapperClass.po.table.name}
        <include refid="queryCondition" />
    </select>

    <!-- 查询总数 -->
    <select id="selectCount" resultType="int">
        SELECT COUNT(*)
        FROM ${mapperClass.po.table.name}
        <include refid="queryCondition" />
    </select>

    <!-- 查询列表(分页) -->
    <select id="selectPageList" resultType="${mapperClass.po.packageName}.${mapperClass.po.className}">
        SELECT
        <include refid="ALL_COLUMNS" />
        FROM ${mapperClass.po.table.name}
        <include refid="queryCondition" />
        ORDER BY createTime DESC
        LIMIT #${r'{'}pageCursor} , #${r'{'}pageSize}
    </select>

    <!-- 所有列 -->
    <sql id="ALL_COLUMNS">
        <#list mapperClass.po.table.columns as column>
            ${column.name}<#if column_has_next>,</#if>
        </#list>
    </sql>

    <!-- 查询条件 -->
    <sql id="queryCondition">
        <where>
            <foreach collection="conditionList" index="index" item="item">
                <choose>
                    <when test="item.condition == 'AND'">
                        ${r'AND ${item.column} = #{item.value}'}
                    </when>
                </choose>
            </foreach>
        </where>
    </sql>

    <!-- 插入语句 -->
    <sql id="insertSQL">
        INSERT INTO ${mapperClass.po.table.name}
        <trim prefix="(" suffix=")" suffixOverrides=",">
        <#list mapperClass.po.table.columns as column>
            <if test="${column.name} != null">
                ${column.name},
            </if>
		</#list>
        </trim>
        <trim prefix="VALUES (" suffix=")" suffixOverrides=",">
        <#list mapperClass.po.table.columns as column>
            <if test="${column.name} != null">
                #${r'{'}${column.name}},
            </if>
		</#list>
        </trim>
    </sql>

</mapper>