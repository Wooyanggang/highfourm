import React, { useState, useEffect } from 'react';
import Container from '../../../Common/PageTitle';
import { BtnBlue, SearchInput, SearchSelectBox } from '../../../Common/Module';
import axios from 'axios';
import { Popconfirm, Input } from "antd";
import BasicTable from '../../../Common/Table/BasicTable';
import PageTitle from '../../../Common/PageTitle';
import { useNavigate, useLocation } from 'react-router';

const ProductionPlan = () => {
  const navigate = useNavigate();
  const location = useLocation();

  const [productionPlans, setProductionPlans] = useState([]);
  const [monthlyProductionPlans, setMonthlyProductionPlans] = useState([]);
  const [selectedProductionPlan, setSelectedProductionPlan] = useState(false);
  const searchParams = new URLSearchParams(location.search);
  const [searchType, setSearchType] = useState('주문 번호');
  const searchTypeParam = searchParams.get('searchType');
  const searchValueParam = searchParams.get('search');
  const currentURL = window.location.pathname;


  useEffect(() => {
    async function fetchData() {
      try {
        let res;

        if (currentURL === '/production-plan/search') {
          res = await axios.get('/api/production-plan/search', {
            params: {
              searchType: searchTypeParam,
              search: searchValueParam,
            },
          });
        } else {
          res = await axios.get('/api/production-plan');
        }

        let productionPlans = res.data
        if (productionPlans && Array.isArray(productionPlans)) {
          productionPlans = productionPlans.map((plan) => ({
            key: plan.productionPlanId,
            productionPlanId: plan.productionPlanId,
            orderId: plan.orderId,
            productName: plan.productName,
            productAmount: plan.productAmount,
            productionPlanAmount: plan.productionPlanAmount,
            orderDate: plan.orderDate,
            productionStartDate: plan.productionStartDate,
            dueDate: plan.dueDate,
            edit: plan.productionPlanAmount ? true : false,
          }));
          setProductionPlans(productionPlans);
        } else {
          console.log('No production plan data received:', res.data);
        }
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    }
    fetchData();
  }, [location, searchTypeParam, searchValueParam]);

  const updateProductionPlan = (key, field, value) => {
    const updatedPlans = productionPlans.map(plan => {
      if (plan.key === key) {
        return { ...plan, [field]: value };
      }
      return plan;
    });
    setProductionPlans(updatedPlans);

    if (selectedProductionPlan && selectedProductionPlan.key === key) {
      setSelectedProductionPlan({ ...selectedProductionPlan, [field]: value });
    }
  };

  const updateMonthlyProductionPlan = (key, value) => {
    const updatedPlans = monthlyProductionPlans.map(plan => {
      if (plan.key === key) {
        return { ...plan, productionAmount: value };
      }
      return plan;
    });
    setMonthlyProductionPlans(updatedPlans);
  };

  const onProductionPlanClick = (record) => {
    axios.get(`/api/production-plan/${record.productionPlanId}`)
      .then(res => {
        const dataFromDB = res.data.map(plan => ({
          ...plan,
          key: `${record.productionPlanId}-${plan.month}`,
          edit: false,
        }));

        if (record.edit) {
          setMonthlyProductionPlans(dataFromDB);
        } else {
          const orderAmount = record.productAmount;
          const { monthsAndDays, totalDays } = getMonthsAndDays(new Date(record.orderDate), new Date(record.dueDate));

          const newPlans = Object.entries(monthsAndDays).map(([month, days]) => ({
            month: month,
            productionAmount: Math.ceil((days / totalDays) * orderAmount),
            key: `${record.productionPlanId}-${month}`,
            edit: true,
          }));
          setMonthlyProductionPlans(newPlans);
        }
      })
      .catch(error => {
        console.error('Error fetching monthly production plans:', error);
      });
    setSelectedProductionPlan(record); // 선택된 생산 계획 상태 업데이트
  };

  const getProductionPlanColumns = (productionPlans) => {
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
        render: text => new Intl.NumberFormat('ko-KR').format(text),
      },
      {
        title: '생산 계획 수량',
        dataIndex: 'productionPlanAmount',
        render: (text, record) => {
          return record.edit ?
          new Intl.NumberFormat('ko-KR').format(text) : 
          (<Input
              defaultValue={text}
              type="number"
              style={{ border: 'none', boxShadow: 'none', backgroundColor: 'transparent' }}
              onChange={e => updateProductionPlan(record.key, 'productionPlanAmount', e.target.value)}
            />)
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
          return record.edit ?
            text :
            <Input
              type="date"
              defaultValue={text}
              style={{ border: 'none', boxShadow: 'none', backgroundColor: 'transparent' }}
              onChange={e => updateProductionPlan(record.key, 'productionStartDate', e.target.value)}
            />
        }
      },
      {
        title: '납기일',
        dataIndex: 'dueDate',
      },
    ];
  };

  const getMonthlyProductionPlanColumns = () => {
    return [
      {
        title: '월',
        dataIndex: 'month',
        width: '50%',
      },
      {
        title: '생산계획 수량',
        dataIndex: 'productionAmount',
        render: (text, record) => (
          record.edit ?
            <Input
              defaultValue={text}
              type="number"
              style={{ border: 'none', boxShadow: 'none', backgroundColor: 'transparent' }}
              onChange={e => updateMonthlyProductionPlan(record.key, e.target.value)}
            /> : new Intl.NumberFormat('ko-KR').format(text)
        ),
      },
    ];
  };

  function getMonthsAndDays(orderDate, dueDate) {
    let monthsAndDays = {};
    let totalDays = 0;
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

      totalDays += daysInMonth;
      monthsAndDays[month] = daysInMonth;
      current = new Date(current.getFullYear(), current.getMonth() + 1, 1);
    }

    return { monthsAndDays, totalDays };
  }

  const handleSave = () => {
    // 선택된 생산 계획에 착수일이 설정되었는지 확인
    if (!selectedProductionPlan || !selectedProductionPlan.productionStartDate) {
      alert('착수일이 설정되지 않았습니다.');
      return;
    }

    if (selectedProductionPlan.productionPlanAmount === null || selectedProductionPlan.productionPlanAmount === 0) {
      alert('생산 수량이 설정되지 않았습니다.');
      return;
    }

    const allMonthlyPlansValid = monthlyProductionPlans.every(plan =>
      !plan.editable || (plan.editable && plan.productionAmount && plan.productionAmount > 0)
    );

    if (!allMonthlyPlansValid) {
      alert('모든 월별 생산 계획 수량을 입력해주세요.');
      return;
    }

    const productionPlanForm = {
      productionPlanId: selectedProductionPlan.productionPlanId,
      productionPlanAmount: selectedProductionPlan.productionPlanAmount,
      productionStartDate: selectedProductionPlan.productionStartDate,
      monthlyProductionPlans: monthlyProductionPlans.map(plan => ({
        productionPlanId: plan.productionPlanId,
        month: plan.month,
        productionAmount: plan.productionAmount
      }))
    };

    // 서버에 데이터 전송
    axios.post('/api/production-plan', productionPlanForm)
      .then(response => {
        alert('저장되었습니다.');
        window.location.reload();
      })
      .catch(error => {
        console.error('Error saving data:', error);
      });
  };


  const SelectChangeHandler = (value) => {
    setSearchType(value);
  };

  const onSearch = (value) => {
    navigate(`/production-plan/search?searchType=${encodeURIComponent(searchType)}&search=${encodeURIComponent(value)}`);
  }

  return (
    <div>
      <PageTitle value={'생산 계획 수립'} />
      <div style={{ display: 'flex', gap: '12px', marginBottom: '15px' }}>
        <SearchSelectBox selectValue={['주문 번호', '거래처명', '품명']} SelectChangeHandler={SelectChangeHandler} />
        <SearchInput id={'search'} name={'search'} onSearch={onSearch} />
      </div>
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
        {selectedProductionPlan &&
          <div className='bordered-box' style={{ width: '20%', height: 'auto' }}>
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
            <div style={{ margin: '10px 0 0 0' }}>
              {!selectedProductionPlan.edit && <BtnBlue value={'저장'} onClick={onclick => handleSave()} />}
            </div>
          </div>
        }
      </div>
    </div>
  );
};

export default ProductionPlan;
