import React from 'react'
import Container from '../../Common/Container'
import { SearchInput, SearchSelectBox } from '../../Common/Module'
import BasicTable from '../../Common/Table'

const Mrp = () => {

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
    <div>
      <Container title={'자재 소요량 산출'}>
        <div>
          <h2>생산계획 조회 조건</h2>
          <SearchSelectBox selectValue={['생산계획 코드', '품번', '품명', '자재명', '자재코드']} SelectChangeHandler={SelectChangeHandler} />
          <SearchInput onSearch={onSearch} />
        </div>
        <div style={{ border: '1px solid #d9d9d9' }}>
          <h2>생산계획 상세</h2>
          <hr style={{ color: '#000' }} />
          <BasicTable></BasicTable>
        </div>
        <div style={{ border: '1px solid #d9d9d9' }}>
          <h2>생산계획 상세</h2>
          <hr style={{ color: '#000' }} />
          <BasicTable></BasicTable>
        </div>
      </Container>
    </div>
  )
}

export default Mrp
