import React, { useState, useEffect } from 'react';
import BasicTable from '../../Common/Table/BasicTable';
import { Input, Popconfirm } from "antd";
import { BtnBlack, BtnGray, InputBar } from '../../Common/Module';
import PageTitle from '../../Common/PageTitle';

function WorkPerfomance() {
  const [dataSource, setDataSource] = useState([]);
  
  useEffect(() => {
    fetch('/work-perfomance')
    .then(response => response.json())
    .then(result => {
        console.log(result);
        if (result) {
          const newData = result.map((item, index) => ({ key: index, ...item }));
          setDataSource(newData);
        } 
      })
      .catch(error => {
        console.error('데이터를 가져오는 중 오류 발생:', error);
      });
  }, []);

  const defaultColumns = [
    {
      title: '생산 계획 코드',
      dataIndex: 'productionPlanId',
    },
    {
      title: '작업 일자',
      dataIndex: 'workDate',
    },
    {
      title: '생산 품목 코드',
      dataIndex: 'productId',
    },
    {
      title: '생산품명',
      dataIndex: 'productName',
    },
    // {
    //   title: '공정',
    //   dataIndex: 'process',
    // },
    {
      title: '생산 수량',
      dataIndex: 'productionAmount',
    },
    {
      title: '양품 수량',
      dataIndex: 'acceptanceAmount',
    },
    {
      title: '불량 수량',
      dataIndex: 'defectiveAmount',
    },
    {
      title: '작업 시간',
      dataIndex: 'workingTime',
    },
    {
      title: '담당자',
      dataIndex: 'manager',
    },
    {
      title: 'Lot No',
      dataIndex: 'lotNo',
    },
    {
      title: '유효 일자',
      dataIndex: 'validDate',
    },
    {
      title: '비고',
      dataIndex: 'note',
    },
  ];

  return (
    <div className='work-perfomance-page'>
        <PageTitle value={'작업 실적 조회'}/>
        <form action='' className='search-form'>
          <div className='search-input-wrap'>
            <div className='search-input'>
              <label htmlFor='workDate'>작업 일자</label>
              <Input type='date' id='workDate' size='large'></Input>
            </div>
            <div className='search-input'>
              <label htmlFor='product-management-id'>생산 계획 번호</label>
              <InputBar id={'product-management-id'}></InputBar>
            </div>
          </div>
          <div className='search-input-wrap'>
            <div className='search-input'>
              <label htmlFor='manager'>담당자</label>
              <InputBar id={'manager'}></InputBar>
            </div>
            <div className='search-input'>
              <label htmlFor='product-name'>생산품명</label>
              <InputBar id={'product-name'}></InputBar>
            </div>
            <BtnGray value={'검색'}> type={'button'}</BtnGray>
          </div>
        </form>
        <div>
          <BasicTable 
          dataSource={dataSource} 
          defaultColumns={defaultColumns} 
          setDataSource={setDataSource} 
          pagination={false}
          />
        </div>
    </div>
  );
}

export default WorkPerfomance