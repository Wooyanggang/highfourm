import React from 'react';
import { Button, Flex, Input, Space, Steps, Select, ConfigProvider } from 'antd';
import '../../basic.css'
const { Search } = Input;

const BtnBlack = ({ value, link, onClick }) => {
  
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
        <Button onClick={onClick}>{value}</Button>
      </Flex>
    </ConfigProvider>
  )
};

const BtnBlue = ({ value, link, onClick}) => {

  return (
    <ConfigProvider
      theme={{
        components: {
          Button: { defaultBg: '#294CC0', defaultColor: '#fff' }
        }
      }}>
      <Flex gap='small' wrap='wrap'>
        <Button onClick={onClick}>{value}</Button>
      </Flex>
    </ConfigProvider>
  )
};

const BtnWhite = ({ value, link, onClick}) => {

  return (
    <Flex gap='small' wrap='wrap'>
      <Button onClick={onClick}>{value}</Button>
    </Flex>
  )
};

const filterClickHandler = (e) => {

}

const BtnFilter = ({ value, link }) => {

  return (
  <ConfigProvider
    theme={{
      token: {
        colorPrimaryHover: '#d9d9d9',
        colorPrimaryActive: '#d9d9d9',
      },
    }}
  >

    <Flex gap='small' wrap='wrap'>
      <Button shape='round' href={`${link}`} onClick={filterClickHandler} id='btnFilter'>{value}</Button>
    </Flex>
  </ConfigProvider>
  )
};

const InputBar = ({ placeholderMsg, inputId, value }) => {
  return (
    <Input placeholder={`${placeholderMsg}`} id={inputId} value={value} style={{width:'200px'}} />
  )
};

const onSearch = (value, _e, info) => {
  console.log(info?.source, value);
}

const SearchInput = () => {
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
        style={{width:'250px'}}
      />
    </Space>
    </ConfigProvider>
  )
};

// const handleChange = (value) => {
//   console.log(`selected ${value}`);
// };

// const SearchSelectBox = ({value}) => {

//   return (
//     <Space wrap>
//       <Select
//         defaultValue={value[0]}
//         style={{
//           width: 120,
//         }}
//         onChange={handleChange}
//         options={[
//           {
//             value: { value[0] },
//             label: { value[0] },
//           },
//           {
//             value: { value[1] },
//             label: { value[1] },
//           },
//           {
//             value: { value[2] },
//             label: { value[2] },
//           },
//         ]}
//       />
//     </ Space>
//   )
// };

const StepBar = ({stateNum}) => {
// stateNum 값은 정수 1,2,3,4
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
        style={{width: 880}}
      />
    </ConfigProvider>
  )
};

// export { BtnBlack, BtnBlue, BtnWhite, BtnFilter, InputBar, SearchInput, SearchSelectBox, StepBar };
export { BtnBlack, BtnBlue, BtnWhite, BtnFilter, InputBar, SearchInput, StepBar };