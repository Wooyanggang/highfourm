import React, { useState } from 'react';
import BasicTable from '../../Common/Table/BasicTable';
import { Tag } from "antd";
import { SearchInput, SearchSelectBox, StepBar } from '../../Common/Module';
import PageTitle from '../../Common/PageTitle';

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
  
  const handleDelete = (key) => {
    const newData = dataSource.filter((item) => item.key !== key);
    setDataSource(newData);
  }
  
  const defaultColumns = [
    {
      title: '주문 번호',
      dataIndex: 'orderID',
      // width: '30%',
      editable: true,
    },
    {
      title: '품명',
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
        <SearchSelectBox selectValue={['주문 번호', '품목']} SelectChangeHandler={SelectChangeHandler} />
        <SearchInput onSearch={onSearch} />
      </div>
      <BasicTable dataSource={dataSource} defaultColumns={defaultColumns} onDelete={handleDelete} setDataSource={setDataSource} />
    </div>
  );
}

export default Status