import React, { useState } from 'react';
import { AudioOutlined } from '@ant-design/icons';
import { Input, Space } from 'antd';
import { DownOutlined, UserOutlined } from '@ant-design/icons';
import { Button, Dropdown, message, Tooltip, Popconfirm, Table, FLex } from 'antd';
import { BtnBlack, BtnBlue, BtnWhite, BtnFilter, InputBar, SearchInput, StepBar } from '../../Common/Module';
import BasicTable from '../../Common/Table/BasicTable';
import '../../../App.css'
const { Search } = Input;
const suffix = (
  <AudioOutlined
    style={{
      fontSize: 16,
      color: '#1677ff',
    }}
  />
);

const OrderList = () => {
  const [dataSource, setDataSource] = useState([]);

  const addOrderProduct = () =>{
    setDataSource([...dataSource,{productName: '',amount: '', unitPrice: ''}]);
    console.log('클릭됨');
    console.log('dataSource: ' + dataSource);
  }

  const handleDelete = (key) => {
    const newData = dataSource.filter((item) => item.key !== key);
    setDataSource(newData);
  }

  const defaultColumns = [
    {
      title: '제품명',
      dataIndex: 'productName',
      width: '250px',
      editable: true,
    },
    {
      title: '수량',
      dataIndex: 'amount',
      width: '150px',
      editable: true,
    },
    {
      title: '단가(원)',
      dataIndex: 'unitPrice',
      width: '150px',
      editable: true,
    },
    {
      title: '합(원)',
      dataIndex: 'price',
      width: '150px',
    },
    {
      title: '삭제',
      dataIndex: 'operation',
      width: '100px',
      render: (_, record) =>
        dataSource.length >= 1 ? (
          <Popconfirm title="Sure to delete?" onConfirm={() => handleDelete(record.key)}>
            <a>Delete</a>
          </Popconfirm>
        ) : null,
    },
  ];
  return (
    <>
      <div style={{ width: '700px', height: '100px', }}>
        <form>
          <div class="order">
            <div class="grid-item">
              <span>
                <label for="vender">거래처명</label>
                <InputBar placeholderMsg={'거래처명'} inputId={'vender'} />
              </span>
              <span>
                <label for="manager">담당자</label>
                <InputBar placeholderMsg={'manager'} inputId={'manager'} />
              </span>
            </div>
            <div class="grid-item">
              <span>
                <label for="orderDate">주문일</label>
                <InputBar placeholderMsg={'orderDate'} inputId={'orderDate'} />
              </span>
              <span>
                <label for="dueDate">납기일</label>
                <InputBar placeholderMsg={'dueDate'} inputId={'dueDate'} />
              </span>
            </div>
          </div>
        </form>
      </div>
      <div style={{width: '750px'}}>
        <BasicTable
          dataSource={dataSource} defaultColumns={defaultColumns}
          onDelete={handleDelete} setDataSource={setDataSource}
        ></BasicTable>
        <BtnBlack value={'제품 추가'} onClick={() => addOrderProduct()} />
      </div>
    </>
  )
}

export default OrderList;