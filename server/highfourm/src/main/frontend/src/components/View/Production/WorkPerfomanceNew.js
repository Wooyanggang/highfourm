import React, { useState } from 'react';
import BasicTable from '../../Common/Table/BasicTable';
import { Input, Popconfirm } from "antd";
import PageTitle from '../../Common/PageTitle';

function WorkPerfomanceNew() {
  const [dataSource, setDataSource] = useState([
    {
      key: 0,
      workDate: '2023-02-02',
      productId: 'E001',
      productName: '데스크탑',
      standard: '',
      process: '보드 케이스 합체',
      productionQuantity: 5,
      goodProductQuantity: 5,
      defectiveProductQuantity: 0,
      workHour: 14,
      managerName: '홍길동',
      LotNo: '',
      availableDate: '',
      productctionPlanId: '201801070001',
      note: ''
    },
    {
      key: 1,
      workDate: '2023-02-02',
      productId: 'E001',
      productName: '데스크탑',
      standard: '',
      process: '보드 케이스 합체',
      productionQuantity: 5,
      goodProductQuantity: 5,
      defectiveProductQuantity: 0,
      workHour: 14,
      managerName: '홍길동',
      LotNo: '',
      availableDate: '',
      productctionPlanId: '201801070001',
      note: ''
    },
  ]);
  
  // console.log(dataSource);
  
  const handleDelete = (key) => {
    const newData = dataSource.filter((item) => item.key !== key);
    setDataSource(newData);
  }
  
  const defaultColumns = [
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
    {
      title: '제품 규격',
      dataIndex: 'standard',
    },
    {
      title: '공정',
      dataIndex: 'process',
    },
    {
      title: '생산 수량',
      dataIndex: 'productionQuantity',
    },
    {
      title: '양품 수량',
      dataIndex: 'goodProductQuantity',
    },
    {
      title: '불량 수량',
      dataIndex: 'defectiveProductQuantity',
    },
    {
      title: '작업 시간',
      dataIndex: 'workHour',
    },
    {
      title: '담당자',
      dataIndex: 'managerName',
    },
    {
      title: 'Lot No',
      dataIndex: 'LotNo',
    },
    {
      title: '유효 일자',
      dataIndex: 'availableDate',
    },
    {
      title: '생산 계획 번호',
      dataIndex: 'productctionPlanId',
    },
    {
      title: '비고',
      dataIndex: 'note',
    },
    {
      title: '삭제',
      dataIndex: 'operation',
      render: (_, record) =>
        dataSource.length >= 1 ? (
          <Popconfirm title="Sure to delete?" onConfirm={() => handleDelete(record.key)}>
            <a>삭제</a>
          </Popconfirm>
        ) : null,
    },
  ];

  return (
    <div className='work-perfomance-page'>
      <PageTitle value={'생산 실적 상세 조회'}/>
        <div style={{width: '1600px'}}>
          <BasicTable dataSource={dataSource} defaultColumns={defaultColumns} onDelete={handleDelete} setDataSource={setDataSource} />
        </div>
    </div>
  );
}

export default WorkPerfomanceNew