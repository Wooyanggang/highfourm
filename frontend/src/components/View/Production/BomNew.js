import React, { useState, useRef } from 'react';
import axios from 'axios';
import { Select } from 'antd';
import { InputBar, BtnBlue, BtnWhite } from '../../Common/Module';

const BomNew = ({  onSubmit, onSubmitSuccess }) => {
  const [timeOptions, setTimeOptions] = useState("");
  const [count, setCount] = useState(0);
  const [dataTable, setDataTable] = useState([]);
  const [dataMaterial, setDataMaterial] = useState([]);

  const formRef = useRef(null);

  const selectTimeOptions = (value) => {
    setTimeOptions(value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // Get form data
    const formData = new FormData(document.getElementById('bomNewForm'));

    const jsonData = {};
    formData.forEach((value, key) => {
      jsonData[key] = value;
    });

    const currentDate = new Date();
    const year = currentDate.getFullYear();
    const month = (currentDate.getMonth() + 1).toString().padStart(2, '0');
    const day = currentDate.getDate().toString().padStart(2, '0');

    const formattedDate = `${year}-${month}-${day}`;
    // 예제: BomRequestDTO와 일치하도록 구성
    const bomRequest = {
      productId: jsonData.productId,
      productName: jsonData.productName,
      writeDate: formattedDate, //현재시간을 문자열로 바꾼 값
      updateDate: formattedDate, //현재시간을 문자열로 바꾼 값
      processId: jsonData.processId,
      sequence: parseInt(jsonData.sequence),
      processName: jsonData.processName,
      timeUnit: jsonData.timeUnit,
      standardWorkTime: parseInt(jsonData.standardWorkTime),
      outputUnit: jsonData.outputUnit,
      materialId: jsonData.materialId,
      inputProcess: jsonData.inputProcess,
      inputAmount: parseInt(jsonData.inputAmount),
    };

    // Send POST request using Axios
    axios.post('/bom/new', JSON.stringify(bomRequest),
      {
        headers: {
          'Content-Type': 'application/json',
        },
      })
      .then(response => {
        // Handle successful response
        console.log('bom data added successfully');
        // Redirect user to another page if needed
      })
      .catch(error => {
        // Handle errors
        console.log(jsonData)
        console.error('Error adding material:', error);
      });

    if (onSubmit) {
      onSubmit();
      // 폼이 성공적으로 제출되면 콜백을 호출합니다.
      if (onSubmitSuccess) {
        onSubmitSuccess();
      }
    }
  };

  const timeUnitOptions = [
    { value: 'sec', label: 'sec' },
    { value: 'min', label: 'min' },
    { value: 'hour', label: 'hour' },
  ];

  return (
    <>
      <form id='bomNewForm' method='post' ref={formRef}  style={{ borderTop: '1px solid #ccc' }} onSubmit={handleSubmit}>
        <div>
          <div style={{ display: 'flex', flexWrap: 'wrap' }}>
          <div className='bordered-box-title' style={{ width:'100%', margin:'24px 0 12px',flexWrap: 'wrap' }}>
            <h3 className='bordered-box-title'>제품</h3>
          </div>
            <div className='modal-div' >
              <label htmlFor='productId' className='label-title'>제품 코드:</label>
              <InputBar inputId={'productId'} name={'productId'} id={'productId'} required/>
            </div>
            <div className='modal-div' style={{ marginBottom: '15px', display: 'flex', alignItems: 'center' }}>
              <label htmlFor='productName' className='label-title'>제품명:</label>
              <InputBar inputId={'productName'} name={'productName'} id={'productName'} required/>
            </div>
          </div>
          <div style={{ display: 'flex', flexWrap: 'wrap' }}>
          <div className='bordered-box-title' style={{ width:'100%', margin:'24px 0 12px',flexWrap: 'wrap' }}>
            <h3 className='bordered-box-title'>공정</h3>
          </div>
            <div className='modal-div' style={{ marginBottom: '15px' }}>
              <label htmlFor='processId' className='label-title'>공정 코드:</label>
              <InputBar inputId={'processId'} name={'processId'} id={'processId'} required/>
            </div>
            <div className='modal-div' style={{ marginBottom: '15px' }}>
              <label htmlFor='sequence' className='label-title'>공정 순서:</label>
              <InputBar inputId={'sequence'} name={'sequence'} id={'sequence'} required/>
            </div>
            <div className='modal-div' style={{ marginBottom: '15px' }}>
              <label htmlFor='processName' className='label-title'>공정명:</label>
              <InputBar inputId={'processName'} name={'processName'} id={'processName'} required/>
            </div>
            <div className='modal-div' style={{ marginBottom: '15px' }}>
              <label htmlFor='timeUnit' className='label-title'>시간 단위:</label>
              {/* <InputBar inputId={'timeUnit'} name={'timeUnit'} id={'timeUnit'} required/> */}
              <Select
                name={'timeUnit'}
                defaultValue="시간 단위"
                style={{
                  width: '200px',
                  height: '40px'
                }}
                onChange={selectTimeOptions}
                options={timeUnitOptions}
                allowClear={false}
                required
              />
              <InputBar type='hidden' name={'timeUnit'} value={timeOptions} />
            </div>
            <div className='modal-div' style={{ marginBottom: '15px' }}>
              <label htmlFor='standardWorkTime' className='label-title'>표준 작업 시간:</label>
              <InputBar inputId={'standardWorkTime'} name={'standardWorkTime'} id={'standardWorkTime'} required/>
            </div>
            <div className='modal-div' style={{ marginBottom: '15px' }}>
              <label htmlFor='outputUnit' className='label-title'>산출물 단위:</label>
              <InputBar inputId={'outputUnit'} name={'outputUnit'} id={'outputUnit'} required/>
            </div>
          </div>
          <div style={{ display: 'flex', flexWrap: 'wrap' }}>
          <div className='bordered-box-title' style={{ width:'100%', margin:'24px 0 12px',flexWrap: 'wrap' }}>
            <h3 className='bordered-box-title'>소요 자재</h3>
          </div>
            <div className='modal-div' >
              <label htmlFor='materialId' className='label-title'>원자재 코드:</label>
              <InputBar inputId={'materialId'} name={'materialId'} id={'materialId'} required/>
            </div>
            <div className='modal-div' style={{ marginBottom: '15px', display: 'flex', alignItems: 'center' }}>
              <label htmlFor='inputProcess' className='label-title'>투입 공정:</label>
              <InputBar inputId={'inputProcess'} name={'inputProcess'} id={'inputProcess'} required/>
            </div>
            <div className='modal-div' style={{ marginBottom: '15px', display: 'flex', alignItems: 'center' }}>
              <label htmlFor='inputAmount' className='label-title'>투입량:</label>
              <InputBar inputId={'inputAmount'} name={'inputAmount'} id={'inputAmount'} required/>
            </div>
          </div>
        </div>
        <div>
          <BtnBlue value={'저장'} type={'submit'} />
        </div>
      </form>
    </>
  );
};
export default BomNew;