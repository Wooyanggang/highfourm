import React, { useState, useEffect } from 'react';
import BasicTable from '../../Common/Table/BasicTable';
import { Tag } from "antd";
import { SearchInput, SearchSelectBox, StepBar } from '../../Common/Module';
import PageTitle from '../../Common/PageTitle';
import KeyTable from '../../Common/Table/KeyTable';

function Status() {
  const [dataSource, setDataSource] = useState([
    {
      key: 0,
      orderID: '2023-11-23-1',
      productName: '밀키스',
      standardDate: '2023-11-23',
      step: 1,
    },
    {
      key: 1,
      orderID: '2023-11-23-2',
      productName: '봉봉',
      standardDate: '2023-11-23',
      step: 2,
    },
  ]);

  useEffect(() => {
    fetch('/production-status')
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
      title: '주문 코드',
      dataIndex: 'orderID',
    },
    {
      title: '제품명',
      dataIndex: 'productName',
    },
    {
      title: '기준 일자',
      dataIndex: 'standardDate',
    },
    {
      title: '진행 단계',
      dataIndex: 'step',
      width: '60%',
      render: (state) => <StepBar stateNum={state}></StepBar>,
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

  return (
    <div className='status-page'>
      <PageTitle value={'생산 현황 조회'}/>
      <div style={{ display: 'flex', flexWrap: 'wrap', gap: '10px 24px', marginBottom: '24px', alignItems: 'center' }}>
        <SearchSelectBox selectValue={['주문 코드', '제품명']} SelectChangeHandler={SelectChangeHandler} />
        <SearchInput onSearch={onSearch} />
      </div>
      <KeyTable
      dataSource={dataSource} 
      defaultColumns={defaultColumns} 
      setDataSource={setDataSource}
      pagination={false} 
      />
    </div>
  );
}

export default Status