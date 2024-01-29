import React, { useState } from 'react';
import { AudioOutlined } from '@ant-design/icons'; //PDF 파일용. 삭제x
import { Input, Space } from 'antd';
import PageTitle from '../../Common/PageTitle';
import { DownOutlined, UserOutlined } from '@ant-design/icons';
import { Button, Dropdown, message, Tooltip, Popconfirm, Table, FLex } from 'antd';
import { BtnBlack, BtnBlue, BtnWhite, BtnFilter, InputBar, SearchInput, StepBar } from '../../Common/Module';
import BasicTable from '../../Common/Table/BasicTable';
import { Document, Page } from 'react-pdf'; //PDF파일용. 삭제 x
const onSearch = (value, _e, info) => console.log(info?.source, value);

const handleButtonClick = (e) => {
  message.info('Click on left button.');
  console.log('click left button', e);
};
const handleMenuClick = (e) => {
  message.info('Click on menu item.');
  console.log('click', e);
};

const items = [
  {
    label: '주문 번호',
    key: '1',
    icon: <UserOutlined />,
  },
  {
    label: '거래처명',
    key: '2',
    icon: <UserOutlined />,
  },
  {
    label: '담당자',
    key: '3',
    icon: <UserOutlined />,
  },
  {
    label: '품목',
    key: '4',
    icon: <UserOutlined />,
  },
];
const menuProps = {
  items,
  onClick: handleMenuClick,
};

const OrderList = () => {
  const [dataSource, setDataSource] = useState([
    {
      key: '0',
      orderId: '24/01/11-1',
      vender: '32',
      manager: '김삼식',
      product: '테스트',
      orderDate: '2023-05-06',
      dueDate: '2024-01-01',
      orderPrice: '7000000',
      registerState: '진행 중',
    },
    {
      key: '1',
      orderId: '24/01/11-1',
      vender: '32',
      manager: 'London, Park Lane no. 0',
      product: '테스트',
      orderDate: '2023-05-06',
      dueDate: '2024-01-01',
      orderPrice: '7000000',
      registerState: '진행 중',
    },
    {
      key: '2',
      orderId: '24/01/11-1',
      vender: '32',
      manager: 'London, Park Lane no. 0',
      product: '테스트',
      orderDate: '2023-05-06',
      dueDate: '2024-01-01',
      orderPrice: '7000000',
      registerState: '진행 중',
    },
    {
      key: '3',
      orderId: '24/01/11-1',
      vender: '32',
      manager: 'London, Park Lane no. 0',
      product: '테스트',
      orderDate: '2023-05-06',
      dueDate: '2024-01-01',
      orderPrice: '7000000',
      registerState: '진행 중',
    },
    {
      key: '4',
      orderId: '24/01/11-1',
      vender: '32',
      manager: 'London, Park Lane no. 0',
      product: '테스트',
      orderDate: '2023-05-06',
      dueDate: '2024-01-01',
      orderPrice: '7000000',
      registerState: '진행 중',
    },
    {
      key: '5',
      orderId: '24/01/11-1',
      vender: '32',
      manager: 'London, Park Lane no. 0',
      product: '테스트',
      orderDate: '2023-05-06',
      dueDate: '2024-01-01',
      orderPrice: '7000000',
      registerState: '진행 중',
    },
    {
      key: '6',
      orderId: '24/01/11-1',
      vender: '32',
      manager: 'London, Park Lane no. 0',
      product: '테스트',
      orderDate: '2023-05-06',
      dueDate: '2024-01-01',
      orderPrice: '7000000',
      registerState: '진행 중',
    },
    {
      key: '7',
      orderId: '24/01/11-1',
      vender: '32',
      manager: 'London, Park Lane no. 0',
      product: '테스트',
      orderDate: '2023-05-06',
      dueDate: '2024-01-01',
      orderPrice: '7000000',
      registerState: '진행 중',
    },
    {
      key: '8',
      orderId: '24/01/11-1',
      vender: '32',
      manager: 'London, Park Lane no. 0',
      product: '테스트',
      orderDate: '2023-05-06',
      dueDate: '2024-01-01',
      orderPrice: '7000000',
      registerState: '진행 중',
    },
    {
      key: '9',
      orderId: '24/01/11-1',
      vender: '32',
      manager: 'London, Park Lane no. 0',
      product: '테스트',
      orderDate: '2023-05-06',
      dueDate: '2024-01-01',
      orderPrice: '7000000',
      registerState: '진행 중',
    },
    {
      key: '10',
      orderId: '24/01/11-1',
      vender: '32',
      manager: 'London, Park Lane no. 0',
      product: '테스트',
      orderDate: '2023-05-06',
      dueDate: '2024-01-01',
      orderPrice: '7000000',
      registerState: '진행 중',
    },
  ]);

  const handleDelete = (key) => {
    const newData = dataSource.filter((item) => item.key !== key);
    setDataSource(newData);
  }


  const defaultColumns = [
    {
      title: '주문 번호',
      dataIndex: 'orderId',
      // width: '30%',
      // editable: true,
    },
    {
      title: '거래처명',
      dataIndex: 'vender',
    },
    {
      title: '담당자',
      dataIndex: 'manager',
    },
    {
      title: '품목',
      dataIndex: 'product',
      render: (text) => <a href='/orders/order'>{text}</a>,
    },
    {
      title: '주문일',
      dataIndex: 'orderDate',
    },
    {
      title: '납기일',
      dataIndex: 'dueDate',
    },
    {
      title: '금액',
      dataIndex: 'orderPrice',
    },
    {
      title: '상태',
      dataIndex: 'registerState',
    },
  ];


  return (
    <>
      <PageTitle value={'주문 관리'}/>
      <Dropdown menu={menuProps}>
        <Button>
          <Space>
            카테고리
            <DownOutlined />
          </Space>
        </Button>
      </Dropdown>
      <SearchInput></SearchInput>
      <onSearch></onSearch>
      <div className='order-list-page'>
        <div className='order-list-wrap'>
          <div className='order-new'>
            <BtnBlack value={'주문 등록'} onClick={() => window.location.href = '/orders/new'} />
          </div>
          <div className='order-filter'>
            <BtnFilter valueArr={['전체', '미확인', '확인', '진행중', '완료']} linkArr={['']} />
          </div>
        </div>
      </div>
      <BasicTable dataSource={dataSource} defaultColumns={defaultColumns} onDelete={handleDelete} setDataSource={setDataSource}/>
    </>
  )
}
export default OrderList;