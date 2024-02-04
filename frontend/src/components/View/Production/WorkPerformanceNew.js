import React, { useState, useEffect } from 'react';
import BasicTable from '../../Common/Table/BasicTable';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { Input, Form, Button, Dropdown, Select, Tooltip, Popconfirm, Table, FLex, Upload } from "antd";
import { BtnBlack, BtnBlue, BtnWhite, BtnFilter, InputBar, SearchInput, StepBar } from '../../Common/Module';
import PageTitle from '../../Common/PageTitle';
import FormItem from 'antd/es/form/FormItem';

function WorkPerformanceNew() {
  const navigate = useNavigate();
  const [count, setCount] = useState(0);
  const [dataSource, setDataSource] = useState([]);
  const [dataProductionPlan, setDataProductionPlan] = useState([]);
  const [options, setOptions] = useState([]);

  const [otherValue, setOtherValue] = useState('');
  

  useEffect(() => {
    fetch('/work-performance/new')
    .then(response => response.json())
    .then(result => {
        console.log(result);
        setDataProductionPlan(result);
        const newOptions = result.map(item => ({
          value: item.productionPlanId,
          label: item.productionPlanId,
        }));
        setOptions(newOptions);
      })
      .catch(error => {
        console.error('데이터를 가져오는 중 오류 발생:', error);
      });
  }, []);

  const workPerformanceNewformSubmit = () => {
    
    const WorkPerformanceNewRequest = dataSource.map(item => ({
      workPerformanceId: null,
      productionPlanId: item.productionPlanId,  
      workDate: item.workDate,
      productionAmount: item.productionAmount,
      acceptanceAmount: item.acceptanceAmount,
      defectiveAmount: item.defectiveAmount,
      workingTime: item.workingTime,
      manager: item.manager,
      lotNo: item.lotNo,
      validDate: item.validDate,
      note: item.note,
    }));

    // 데이터 전송 로직
    axios.post('/work-performance/new', WorkPerformanceNewRequest, {
      headers: {
        'Content-Type': 'application/json'
      }
    })
    .then(response => {
      alert('성공적으로 등록되었습니다.');
      navigate('/work-performance')
    })
    .catch(error => {
      alert('등록에 실패하였습니다.');
      console.log(WorkPerformanceNewRequest);
    });
  };

  const handleDelete = (key) => {
    setDataSource((prevState) => prevState.filter((item) => item.key !== key));
  }

  const handleAdd = () => {
    const newData = {
      key: count,
    };
    setDataSource(prevState => [ ...prevState, newData ]);
    setCount(count + 1);
  };
  
  const defaultColumns = [
    {
      title: '생산 계획 코드',
      dataIndex: 'productionPlanId',
      render: (text, record) => (
        <Form.Item name='productionPlanId' style={{ margin: 0 }}>
          <Select
            showSearch
            name='productionPlanId'
            style={{ width: '100%' }}
            variant='borderless'
            placeholder="Search to Select"
            optionFilterProp="children"
            filterOption={(input, option) => (option?.label ?? '').includes(input)}
            filterSort={(optionA, optionB) =>
              (optionA?.label ?? '').toLowerCase().localeCompare((optionB?.label ?? '').toLowerCase())
            }
            options={options}
            onChange={(value) => handleSelectChange(value, record.key) }
            />
          </Form.Item>
        ),
    },
    {
      title: '작업 일자',
      dataIndex: 'workDate',
      render: () => (
        <Form.Item name='workDate' style={{ margin: 0 }}>
          <Input variant="borderless" type='date' />
        </Form.Item>
        ),
        
    },
    {
      title: '생산 품목 코드',
      dataIndex: 'productId',
    },
    {
      title: '생산 품명',
      dataIndex: 'productName',
    },
    // {
    //   title: '공정',
    //   dataIndex: 'process',
    //   editable: true,
    // },
    {
      title: '생산 수량',
      dataIndex: 'productionAmount',
      editable: true,
    },
    {
      title: '양품 수량',
      dataIndex: 'acceptanceAmount',
      editable: true,
    },
    {
      title: '불량 수량',
      dataIndex: 'defectiveAmount',
      editable: true,
    },
    {
      title: '작업 시간',
      dataIndex: 'workingTime',
      editable: true,
    },
    {
      title: '담당자',
      dataIndex: 'manager',
      editable: true,
    },
    {
      title: 'Lot No',
      dataIndex: 'lotNo',
      editable: true,
    },
    {
      title: '유효 일자',
      dataIndex: 'validDate',
      render: () => (
        <Form.Item name='validDate' style={{ margin: 0 }}>
          <Input variant="borderless" type='date' name='validDate'/>
        </Form.Item>
        ),
    },
    {
      title: '비고',
      dataIndex: 'note',
      editable: true,
    },
    {
      title: '삭제',
      dataIndex: 'operation',
      render: (_, record) =>
        dataSource.length >= 1 ? (
          <Popconfirm title="정말 삭제하시겠습니까?" onConfirm={() => handleDelete(record.key)}>
            <a>삭제</a>
          </Popconfirm>
        ) : null,
    },
  ];
  const handleSelectChange = (value, key) => {
    const selectedPlan = dataProductionPlan.find(plan => plan.productionPlanId === value);
    // Update the corresponding productName in dataSource
    const newData = dataSource.map(item => {
      if (item.key === key) {
        return {
          ...item,
          productId: selectedPlan?.productId || '',
        productName: selectedPlan?.productName || '',
        };
      }
      return item;
    });
  
    setDataSource(newData);
  };

  return (
    <div className='work-performance-new-page'>
      <PageTitle value={'작업 실적 등록'}/>
      <div>
        <BasicTable 
        dataSource={dataSource} 
        defaultColumns={defaultColumns} 
        setDataSource={setDataSource} 
        pagination={false}/>
      </div>
      <div>
        <div className='add-btn'>
          <BtnBlack value={"작업 실적 추가"} onClick={handleAdd}></BtnBlack>
        </div>
        <div>
          <div className='cancel-btn'>
            <BtnWhite value={'취소'} onClick={e =>navigate('/work-performance')}/>
          </div>
          <div className='submit-btn'>
                <BtnBlue value={'작업 실적 등록'} onClick={workPerformanceNewformSubmit} />
              </div>
        </div>
      </div>
    </div>
  );
}

export default WorkPerformanceNew