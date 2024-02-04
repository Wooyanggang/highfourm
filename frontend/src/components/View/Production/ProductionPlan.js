import React, { useState, useEffect } from 'react';
import Container from '../../Common/PageTitle';
import { BtnBlack, SearchInput, SearchSelectBox } from '../../Common/Module';
import axios from 'axios';
import { Popconfirm, Input } from "antd";
import BasicTable from '../../Common/Table/BasicTable';
import PageTitle from '../../Common/PageTitle';

const ProductionPlan = () => {

  const [productionPlans, setProductionPlans] = useState([]);
  const [monthlyProductionPlans, setMonthlyProductionPlans] = useState([]);

  useEffect(() => {
    axios.get('/production-plan')
      .then(res => {
        const productionPlan = res.data;
        if (productionPlan) {
          setProductionPlans(productionPlan);
        } else {
          console.log('No production plan data received:', res.data);
        }
      })
      .catch(error => {
        console.error('Error fetching data:', error);
      });
  }, []);

  const onProductionPlanClick = (record) => {
    axios.get(`/production-plan/${record.productionPlanId}`)
      .then(res => {
        if (res.data && res.data.length > 0) {
          setMonthlyProductionPlans(res.data);
        } else {
          const orderDate = new Date(record.orderDate);
          const dueDate = new Date(record.dueDate);
          const calculatedMonthsAndDays = getMonthsAndDays(orderDate, dueDate);

          const newPlans = Object.entries(calculatedMonthsAndDays).map(([month, days]) => ({
            month: month,
            productionAmount: days,
          }));
          setMonthlyProductionPlans(newPlans);
        }
      })
      .catch(error => {
        console.error('Error fetching monthly production plans:', error);
      });
  };

  const getProductionPlanColumns = (productionPlan) => {
    return [
      {
        title: '생산계획 코드',
        dataIndex: 'productionPlanId',
        width: '20%',
      },
      {
        title: '주문 번호',
        dataIndex: 'orderId',
      },
      {
        title: '품명',
        dataIndex: 'productName',
      },
      {
        title: '주문 수량',
        dataIndex: 'productAmount',
      },
      {
        title: '생산 계획 수량',
        dataIndex: 'productionPlanAmount',
        render: (text, record) => {
          return record.productionPlanAmount ? text : (
            <Input
              defaultValue={text}
              type='number'
              style={{ border: 'none', boxShadow: 'none', backgroundColor: 'transparent' }}
            />
          );
        },
      },
      {
        title: '주문일',
        dataIndex: 'orderDate',
      },
      {
        title: '착수일',
        dataIndex: 'productionStartDate',
        render: (text, record) => {
          return record.productionStartDate ? text : (
            <Input
              type='date'
              defaultValue={text}
              style={{ border: 'none', boxShadow: 'none', backgroundColor: 'transparent' }}
            />
          );
        },
      },
      {
        title: '납기일',
        dataIndex: 'dueDate',
      },
    ];
  };

  function getMonthsAndDays(orderDate, dueDate) {
    let monthsAndDays = {};
    let current = new Date(orderDate.getFullYear(), orderDate.getMonth(), 1);

    while (current <= dueDate) {
      let lastDayOfMonth = new Date(current.getFullYear(), current.getMonth() + 1, 0);
      let month = `${current.getFullYear().toString().substr(2, 2)}-${(current.getMonth() + 1).toString().padStart(2, '0')}`;
      let daysInMonth;

      if (current.getMonth() === orderDate.getMonth() && current.getFullYear() === orderDate.getFullYear()) {
        if (lastDayOfMonth <= dueDate) {
          daysInMonth = lastDayOfMonth.getDate() - orderDate.getDate() + 1;
        } else {
          daysInMonth = dueDate.getDate() - orderDate.getDate() + 1;
        }
      } else if (current.getMonth() === dueDate.getMonth() && current.getFullYear() === dueDate.getFullYear()) {
        daysInMonth = dueDate.getDate();
      } else {
        daysInMonth = lastDayOfMonth.getDate();
      }

      monthsAndDays[month] = daysInMonth;
      current = new Date(current.getFullYear(), current.getMonth() + 1, 1);
    }

    return monthsAndDays;
  }

  const getMonthlyProductionPlanColumns = () => {
    const columns = [
      {
        title: '월',
        dataIndex: 'month',
        width: '50%',
        editable: false
      },
      {
        title: '생산계획 수량',
        dataIndex: 'productionAmount',
        editable: monthlyProductionPlans.length === 0
      },
    ];

    if (monthlyProductionPlans.length > 0 && monthlyProductionPlans.some(plan => plan.hasOwnProperty('editable'))) {
      return columns.map(col => ({
        ...col,
        editable: true,
      }));
    } else {
      return columns.map(col => ({
        ...col,
        editable: false,
      }));
    }
  };

  const monthlyProductionPlanColumns = [
    {
      title: '월',
      dataIndex: 'month',
      width: '50%',
    },
    {
      title: '생산계획 수량',
      dataIndex: 'productionAmount',
    },
  ];

  const SelectChangeHandler = (value) => {
    console.log(`selected ${value}`);
    // select 값 선택시 기능 구현 핸들러
    // 각 페이지에서 구현해주세요
  };

  const onSearch = (value, _e, info) => {
    // search 값 기능 구현 함수
    console.log(info?.source, value);
  }

  const [count, setCount] = useState(productionPlans.length);
  const handleAdd = () => {
    const newData = {
      key: count,
      product_plan_code: `PP24011101${count}`,
      product_name: '키보드',
      procution_unit: 'EA',
      amount: '500',
      oder_date: '2024-01-11',
      production_start_date: '2024-01-12',
      due_date: '2024-03-02',
    };
    setProductionPlans([...productionPlans, newData]);
    setCount(count + 1);
  };

  console.log(productionPlans)
  return (
    <div>
      <PageTitle value={'생산 계획 수립'} />
      {/* <div style={{ display: 'flex', gap: '12px', marginBottom: '15px' }}>
        <SearchSelectBox selectValue={['생산계획 코드', '품명', '주문일']} SelectChangeHandler={SelectChangeHandler} />
        <SearchInput id={'search'} name={'search'} onSearch={onSearch} />
      </div>
      <div style={{ marginBottom: '24px' }}>
        <BtnBlack value={'생산계획 등록'} onClick={handleAdd} />
      </div> */}
      <div style={{ display: 'flex', gap: '24px 19px' }}>
        <div style={{ paddingRight: '20px' }}>
          <BasicTable
            dataSource={productionPlans}
            defaultColumns={getProductionPlanColumns(productionPlans)}
            setDataSource={setProductionPlans}
            pagination={true}
            onRowClick={(record, rowIndex, event) => {
              console.log("Row clicked:", record, rowIndex);
              onProductionPlanClick(record);
            }}
          />
        </div>
        <div className='bordered-box'>
          <div className='bordered-box-title' style={{ marginBottom: '30px' }}>
            <h2 className='bordered-box-title'>월별 생산 계획</h2>
            <hr className='box-title-line' />
          </div>
          <BasicTable
            dataSource={monthlyProductionPlans}
            defaultColumns={getMonthlyProductionPlanColumns()}
            setDataSource={setMonthlyProductionPlans}
            pagination={false}
          />
        </div>
      </div>
    </div>
  );
};

export default ProductionPlan;
