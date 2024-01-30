import React, { useState, useRef } from 'react';
import { Select } from 'antd';
import { InputBar } from '../../Common/Module';

const StockNew = ({ formAction, onSubmit }) => {
  const [manageValue, setManageValue] = useState("");
  const [leadTimeDisabled, setLeadTimeDisabled] = useState(false);
 
  const formRef = useRef(null);

  const selectValue = (value) => {
    setManageValue(value);
    setLeadTimeDisabled(value === '3');
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (onSubmit) {
      onSubmit();
    }
  };

  const ManageOptions= [
    {
      value: '1',
      label: '정량',
    },
    {
      value: '2',
      label: '정기',
    },
    {
      value: '3',
      label: '수동',
    },
  ]

  return (
    <>
      <form id='stockNewForm' method='post' ref={formRef} action={formAction} style={{ borderTop: '1px solid #ccc' }} onSubmit={handleSubmit}>
        <div style={{ display: 'flex', flexDirection: 'row' }}>
          <div className='modal-line' style={{ marginRight: '40px' }}>
            <div className='modal-div' >
              <label htmlFor='materialId' className='label-title'>자재코드:</label>
              <InputBar inputId={'materialId'} name={'materialId'} id={'materialId'} placeholderMsg={'자재코드'} />
            </div>
            <div className='modal-div' style={{ marginBottom: '15px', display: 'flex', alignItems: 'center' }}>
              <label htmlFor='unit' className='label-title'>단위:</label>
              <InputBar inputId={'unit'} name={'unit'} id={'unit'} placeholderMsg={'단위'} />
            </div>
            <div className='modal-div' style={{ marginBottom: '15px' }}>
              <label htmlFor='managementId' className='label-title'>재고관리 방식:</label>
              <Select 
                name={'managementId'}
                defaultValue="재고관리 방식 선택"
                style={{
                  width: '200px',
                  height: '40px'
                }}
                onChange={selectValue}
                options={ManageOptions}
                allowClear={false}
                required
              />
              <InputBar type='hidden' name={'managementId'} value={manageValue} />
            </div>
            <div className='modal-div' style={{ marginBottom: '15px' }}>
              <label htmlFor='maxStock' className='label-title'>최대재고:</label>
              <InputBar inputId={'maxStock'} name={'maxStock'} id={'maxStock'} placeholderMsg={'최대재고'} />
            </div>
          </div>

          <div className='modal-line'>
            <div className='modal-div' style={{ marginBottom: '15px' }}>
              <label htmlFor='materialName' className='label-title'>자재명:</label>
              <InputBar inputId={'materialName'} name={'materialName'} id={'materialName'} placeholderMsg={'자재명'} required/>
            </div>
            <div className='modal-div' style={{ marginBottom: '15px' }}>
              <label htmlFor='totalStock' className='label-title'>총재고량:</label>
              <InputBar inputId={'totalStock'} name={'totalStock'} id={'totalStock'} placeholderMsg={'총재고량'} />
            </div>
            <div className='modal-div' style={{ marginBottom: '15px' }}>
              <label htmlFor='safetyStock' className='label-title'>안전재고:</label>
              <InputBar inputId={'safetyStock'} name={'safetyStock'} id={'safetyStock'} placeholderMsg={'안전재고'} disabled={leadTimeDisabled}/>
            </div>
            <div className='modal-div' style={{ marginBottom: '15px' }}>
              <label htmlFor='LeadTime' className='label-title'>LeadTime:</label>
              <InputBar inputId={'LeadTime'} name={'LeadTime'} id={'LeadTime'} placeholderMsg={'LeadTime'} disabled={leadTimeDisabled}/>
            </div>
          </div>
        </div>
        <button type='submit' value={'버튼입니다ㅏㅏㅏㅏㅏㅏ'}/>
      </form>
    </>
  );
};
export default StockNew;