import React, { useState, useEffect } from 'react';
import { AudioOutlined } from '@ant-design/icons'; //PDF 파일용. 삭제x

import { Space } from 'antd';
import axios from 'axios';
import PageTitle from '../../Common/PageTitle';
import { DownOutlined, UserOutlined } from '@ant-design/icons';
import { Button, Dropdown, message } from 'antd';
import { BtnBlack, BtnFilter, SearchInput } from '../../Common/Module';
import BasicTable from '../../Common/Table/BasicTable';
import { Document, Page } from 'react-pdf'; //PDF파일용. 삭제 x
const onSearch = (value, _e, info) => console.log(info?.source, value);

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
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    axios.get('/orders')
      .then(res => {
        if (res.data.orders && res.data.ordersDetail) {
          const updatedOrders = res.data.orders.map(order => {
            const details = res.data.ordersDetail.filter(detail => detail.orderId === order.orderId);
            return { ...order, details };
          });
          setOrders(updatedOrders);
        } else {
          console.log('Unexpected data structure:', res.data);
        }
      })
      .catch(error => {
        console.error('Error fetching data:', error);
      });
  }, []);

  const defaultColumns = [
    {
      title: '주문 번호',
      dataIndex: 'orderId',
      // width: '30%',
      // editable: true,
    },
    {
      title: '거래처명',
      dataIndex: 'vendor',
    },
    {
      title: '담당자',
      dataIndex: 'manager',
    },
    {
      title: '품목',
      dataIndex: 'details',
      render: details => {
        if (details.length === 1) {
          return <span>{details[0].productName}</span>;
        } else {
          return <span>{`${details[0].productName} 외 ${details.length - 1}건`}</span>;
        }
      }
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
      dataIndex: 'details',
      render: details => {
        const totalPrice = details.reduce((sum, detail) => {
          return sum + (detail.productAmount * detail.unitPrice);
        }, 0);
        return <span>{totalPrice.toLocaleString()}원</span>;
      }
    },
    {
      title: '상태',
      dataIndex: 'endingState',
      render: endingState => (endingState ? "완료" : "진행중")
    },
  ];

  console.log(orders);

  return (
    <>
      <PageTitle value={'주문 관리'} />
      {/* <Dropdown menu={menuProps}>
        <Button>
          <Space>
            카테고리
            <DownOutlined />
          </Space>
        </Button>
      </Dropdown>
      <SearchInput></SearchInput>
      <onSearch></onSearch> */}
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
      <BasicTable dataSource={orders} defaultColumns={defaultColumns} />
    </>
  )
}
export default OrderList;