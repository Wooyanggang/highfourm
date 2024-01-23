import React, { useState } from 'react';
import { Button, Flex, Input, Space, Steps, Select, ConfigProvider } from 'antd';
import '../../basic.css'
const { Search } = Input;

const BtnBlack = ({ value, link }) => {
  // <BtnBlack value={'검정'} link={'/black'} />

  return (
    <ConfigProvider
      theme={{
        components: {
          Button: { defaultBg: '#000', defaultColor: '#fff' },
        },
        token: {
          colorPrimaryHover: '#d9d9d9',
          colorPrimaryActive: '#d9d9d9',
        },
      }}>
      <Flex gap='small' wrap='wrap'>
        <Button href={`${link}`}>{value}</Button>
      </Flex>
    </ConfigProvider>
  )
};

const BtnBlue = ({ value, link }) => {
  // <BtnBlue value={'파랑'} link={'/blue'} />

  return (
    <ConfigProvider
      theme={{
        components: {
          Button: { defaultBg: '#294CC0', defaultColor: '#fff' }
        }
      }}>
      <Flex gap='small' wrap='wrap'>
        <Button href={`${link}`}>{value}</Button>
      </Flex>
    </ConfigProvider>
  )
};

const BtnWhite = ({ value, link }) => {
  // <BtnWhite value={'흰색'} link={'/white'} />
  return (
    <Flex gap='small' wrap='wrap'>
      <Button href={`${link}`}>{value}</Button>
    </Flex>
  )
};

const BtnFilter = ({ valueArr, linkArr }) => {
  // <BtnFilter valueArr={['완료', '전체']} linkArr={['/complete', '/all']} />
  const [activeBtns, setActiveBtns] = useState(Array(valueArr.length).fill(false));

  const filterClickHandler = (index) => {
    // 클릭시 기능 구현 핸들러
    const newActiveBtns = [...activeBtns];
    newActiveBtns[index] = !newActiveBtns[index];
    setActiveBtns(newActiveBtns);
  }

  return (
    <ConfigProvider
      theme={{
        token: {
          colorPrimaryHover: '#d9d9d9',
          colorPrimaryActive: '#d9d9d9',
        },
      }}>
      <Flex gap='small' wrap='wrap'>
        {valueArr.map((value, index) => {
          return (<Button shape='round' href={linkArr[index]} onClick={() => filterClickHandler(index)}
            className={activeBtns[index] ? 'filter-active' : ''} key={index}>
            {value}
          </Button>
          )
        })
        }
      </Flex>
    </ConfigProvider>
  )
};

const InputBar = ({ placeholderMsg, inputId, value }) => {
  return (
    <Input placeholder={`${placeholderMsg}`} id={inputId} value={value} style={{ width: '200px' }} />
  )
};

const onSearch = (value, _e, info) => {
  // search 값 기능 구현 함수
  console.log(info?.source, value);
}

const SearchInput = ({ onSearch }) => {
  return (
    <ConfigProvider
      theme={{
        token: {
          colorPrimary: '#d9d9d9',
        },
        components: {
          Button: {
            primaryColor: '#000',
          },
        },
      }}>
      <Space direction='vertical'>
        <Search
          placeholder='검색어를 입력하세요.'
          allowClear
          enterButton='검색'
          onSearch={onSearch}
          style={{ width: '250px' }}
        />
      </Space>
    </ConfigProvider>
  )
};

const SelectChangeHandler = (value) => {
  console.log(`selected ${value}`);
  // select 값 선택시 기능 구현 핸들러
  // 각 페이지에서 구현해주세요
};

const SearchSelectBox = ({ selectValue, SelectChangeHandler }) => {
  // value 값 배열로 넘기기 <SearchSelectBox selectValue={['가', '나', '다']} SelectChangeHandler={SelectChangeHandler} /> 

  return (
    <Space wrap>
      <Select
        defaultValue={selectValue[0]}
        style={{
          width: 120,
        }}
        onChange={SelectChangeHandler}
        options={selectValue.map((e, index) => ({
          value: `${e}`,
          label: `${e}`,
          key: index,
        }))}
      />
    </ Space>
  )
};

const StepBar = ({ stateNum }) => {
  // stateNum 값은 정수 1,2,3,4
  // <StepBar stateNum={1} />

  return (
    <ConfigProvider
      theme={{
        components: {
          Steps: {
            navArrowColor: 'rgba(0, 0, 0, 0.45)',
            finishIconBorderColor: '#1677ff',
          },
        },
      }}
    >
      <Steps
        current={stateNum}
        items={[
          {
            title: '주문서',
          },
          {
            title: '생산 준비',
          },
          {
            title: '생산중',
          },
          {
            title: '완료',
          },
        ]}
        style={{ width: 880 }}
      />
    </ConfigProvider>
  )
};

export { BtnBlack, BtnBlue, BtnWhite, BtnFilter, InputBar, SearchInput, SearchSelectBox, StepBar };