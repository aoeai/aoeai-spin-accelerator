package com.aoeai.spin.accelerator.refining.db.service;

import com.aoeai.spin.accelerator.refining.db.bean.Column;
import com.aoeai.spin.accelerator.refining.db.bean.Table;
import com.aoeai.spin.accelerator.refining.db.config.IDbConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * MySQL 数据库的表的服务
 */
@Deprecated
public class MySqlDBTableServiceImpl implements DBTableService {

    /**
     * 数据库配置信息
     */
    private IDbConfig dbConf;

    public MySqlDBTableServiceImpl(IDbConfig dbConf) {
        this.dbConf = dbConf;
    }

    @Override
    public Map<String, Table> allTables() {
        String sql = "select table_name , column_name ,  column_type , column_key , extra , is_nullable ,column_comment, "
                + "( select tables.table_comment from tables where tables.table_name = columns.table_name and tables.table_schema = '"
                + dbConf.database()
                + "') as table_comment"
                + " from columns where table_schema='"
                + dbConf.database() + "' order by table_name";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Map<String, Table> tablesMap = new LinkedHashMap<>();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://"
                            + dbConf.host() + ":" + dbConf.port()
                            + "/information_schema", dbConf.user(),
                    dbConf.password());
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String tableName = resultSet.getString("table_name");

                Table table = tablesMap.get(tableName);

                if (table == null) {
                    table = new Table();
                    table.setName(tableName);
                    table.setComment(resultSet
                            .getString("table_comment"));
                    table.setColumns(new ArrayList<>());
                }

                Column column = new Column();
                column.setName(resultSet.getString("column_name"));
                column.setType(resultSet.getString("column_type"));
                column.setPrimaryKey(resultSet.getString("column_key").equals("PRI"));
                column.setNullable("YES".equals(resultSet.getString("is_nullable")));
                column.setComment(resultSet.getString("column_comment"));
                table.getColumns().add(column);

                tablesMap.put(tableName, table);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (resultSet != null && !resultSet.isClosed()) {
                    resultSet.close();
                }

                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }

                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }

        return tablesMap;
    }
}
