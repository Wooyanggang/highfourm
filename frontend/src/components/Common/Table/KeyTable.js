import React, { useState } from 'react';
import { Table } from 'antd';
import EditableRow from './EditableRow';
import EditableCell from './EditableCell';
import { useNavigate } from 'react-router-dom';

const KeyTable = ({ dataSource, defaultColumns, setDataSource, pagination, url, keyName }) => {
  const [recordKey, setRecordKey] = useState();
  const [activeIndex, setActiveIndex] = useState();
  const navigate = useNavigate();
  const handleSave = (row) => {
    const newData = [...dataSource];
    const index = newData.findIndex((item) => row.key === item.key);
    const item = newData[index];
    newData.splice(index, 1, {
      ...item,
      ...row,
    });
    setDataSource(newData);
  };
  const components = {
    body: {
      row: EditableRow,
      cell: EditableCell,
    },
  };
  const columns = defaultColumns.map((col) => {
    if (!col.editable) {
      return col;
    }
    return {
      ...col,
      onCell: (record) => ({
        record,
        editable: col.editable,
        dataIndex: col.dataIndex,
        title: col.title,
        handleSave,
      }),
    };
  });

  const clickRow = (record, index) => {
    setRecordKey(record);
    console.log(recordKey);
    setActiveIndex(index); // activeIndex를 업데이트
  };

  return (
    <div>
      <Table
        className="clickable-table"
        components={components}
        rowClassName={() => 'editable-row'}
        bordered
        dataSource={dataSource}
        columns={columns}
        size="middle"
        pagination={pagination}
        onRow={(record, index) => ({
          onClick: () => {
            const newUrl = `/${url}/${record[keyName]}`;
            navigate(newUrl);
          },
        })}
      />
    </div>
  );
};
export default KeyTable;