import React, { useEffect, useState } from 'react';
import { Button, Flex, Input, Space, Steps, Select, ConfigProvider } from 'antd';
const { Search } = Input;

const BtnGray = ({ value, onClick, type }) => {
  // <BtnGray value={'회색'} onClick={onClick} />

  return (
    <ConfigProvider
      theme={{
        components: {
          Button: { defaultBg: '#d9d9d9', defaultColor: '#222' },
        },
        token: {
          colorPrimaryHover: '#222',
          colorPrimaryActive: '#222',
        },
      }}>
      <Flex gap='small' wrap='wrap'>
        <Button size='large' htmlType={type} onClick={onClick}>{value}</Button>
      </Flex>
    </ConfigProvider>
  )
};

const BtnBlack = ({ value, onClick, type }) => {
  // <BtnBlack value={'검정'} onClick={onClick} />

  return (
    <ConfigProvider
      theme={{
        components: {
          Button: { defaultBg: '#222', defaultColor: '#fff' },
        },
        token: {
          colorPrimaryHover: '#d9d9d9',
          colorPrimaryActive: '#d9d9d9',
        },
      }}>
      <Flex gap='small' wrap='wrap'>
        <Button size='large' htmlType={type} onClick={onClick}>{value}</Button>
      </Flex>
    </ConfigProvider>
  )
};
const BtnLogin = ({ value, onClick, type }) => {
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
      <Flex vertical gap="small" style={{ width: '100%' }}>
        <Button size='large' htmlType={type} onClick={onClick} block>{value}</Button>
      </Flex>
    </ConfigProvider>
  )
}

const BtnBlackLarge = ({ value, onClick, type }) => {
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
      <Flex vertical gap="small" style={{ width: '100%' }}>
        <Button size='large' htmlType={type} onClick={onClick}>{value}</Button>
      </Flex>
    </ConfigProvider>
  )
}

const BtnBlue = ({ value, onClick, type }) => {
  // <BtnBlue value={'파랑'} onClick={onClick} />

  return (
    <ConfigProvider
      theme={{
        components: {
          Button: { defaultBg: '#294CC0', defaultColor: '#fff' }
        }
      }}>
      <Flex gap='small' wrap='wrap'>
        <Button size='large' htmlType={type} onClick={onClick}>{value}</Button>
      </Flex>
    </ConfigProvider>
  )
};

const BtnWhite = ({ value, onClick, type }) => {
  // <BtnWhite value={'흰색'} onClick={onClick} />
  return (
    <Flex gap='small' wrap='wrap'>
      <Button size='large' htmlType={type} onClick={onClick}>{value}</Button>
    </Flex>
  )
};

const BtnFilter = ({ valueArr, linkArr, type }) => {
  // <BtnFilter valueArr={['완료', '전체']} linkArr={['/complete', '/all']} />
  const [activeBtnIndex, setActiveBtnIndex] = useState(0);
  const currentURL = window.location.pathname;

  const filterClickHandler = (e, index) => {
    // 클릭시 기능 구현 핸들러
    setActiveBtnIndex(index);
    console.log("handler : ", index);
  }

  useEffect(() => {
    const indexKey = valueArr.findIndex((_, index) => currentURL === linkArr[index]);
    if (indexKey !== -1) {
      setActiveBtnIndex(indexKey);
    }
  }, [currentURL, valueArr]);

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
          return (<Button shape='round' href={linkArr[index]} htmlType={type} onClick={(e) => filterClickHandler(e, index)}
            className={activeBtnIndex === index ? 'filter-active' : ''} key={index} size='large'>
            {value}
          </Button>
          )
        })
        }
      </Flex>
    </ConfigProvider>
  )
};

const InputBar = ({ type, id, name, value, placeholderMsg, onChange, disabled, required, readOnly }) => {
  // disabled={ true | false }
  if (placeholderMsg == null) {
    placeholderMsg = '';
  }

  return (
    <Input type={type} id={id} name={name} value={value} placeholder={`${placeholderMsg}`} style={{ width: '200px' }} size='large' onChange={onChange} disabled={disabled} required={required} readOnly={readOnly} />
  )
};

const SignInputBar = ({ type, id, value, name, placeholderMsg, disabled }) => {
  // disabled={ true | false }
  if (placeholderMsg == null) {
    placeholderMsg = '';
  }

  return (
    <Input type={type} id={id} name={name} value={value} placeholder={`${placeholderMsg}`} style={{ width: '300px' }} size='large' disabled={disabled} />
  )
};

const onSearch = (value, _e, info) => {
  // search 값 기능 구현 함수
  console.log(info?.source, value);
}

const SearchInput = ({ id, name, onSearch, onChange }) => {
  return (
    <ConfigProvider
      theme={{
        token: {
          colorPrimary: '#d9d9d9',
          colorTextLightSolid: '#222',
        },
        components: {
          Button: {
            primaryColor: '#222',
          },
        },
      }}>
      <Space direction='vertical'>
        <Search
          id={id}
          name={name}
          placeholder='검색어를 입력하세요.'
          allowClear
          enterButton='검색'
          onSearch={onSearch}
          onChange={onChange}
          style={{ width: '250px' }}
          size='large'
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

const SearchSelectBox = ({ id, name, selectValue, SelectChangeHandler }) => {
  // value 값 배열로 넘기기 <SearchSelectBox selectValue={['가', '나', '다']} SelectChangeHandler={SelectChangeHandler} /> 

  return (
    <Space wrap>
      <Select
        id={id}
        name={name}
        defaultValue={selectValue[0]}
        style={{
          width: 150,
        }}
        onChange={SelectChangeHandler}
        options={selectValue.map((e, index) => ({
          value: `${e}`,
          label: `${e}`,
          key: index,
        }))}
        size='large'
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
        style={{ width: '100%' }}
      />
    </ConfigProvider>
  )
};

export { BtnGray, SignInputBar, BtnLogin, BtnBlack, BtnBlackLarge, BtnBlue, BtnWhite, BtnFilter, InputBar, SearchInput, SearchSelectBox, StepBar };
