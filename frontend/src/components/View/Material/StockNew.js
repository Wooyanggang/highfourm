import React from 'react';
import { InputBar } from '../../Common/Module';

const StockNew = ({ formAction }) => {
  
  return (
    <>
      <form id='stockNewForm' method='post' action={formAction} style={{ borderTop: '1px solid #ccc' }}>
        <div style={{display: 'flex', flexDirection: 'row'}}>
          <div  className='modal-line' style={{ marginRight: '40px' }}>
            <div className='modal-div' >
              <label htmlFor='empNo' className='label-title'>자재코드:</label>
              <InputBar inputId={'empNo'} id={'empNo'} placeholderMsg={'자재코드'} />
            </div>
            <div className='modal-div' style={{ marginBottom: '15px', display: 'flex', alignItems: 'center' }}>
              <label htmlFor='position' className='label-title'>단위:</label>
              <InputBar inputId={'position'} id={'position'} placeholderMsg={'단위'} />
            </div>
            <div className='modal-div' style={{ marginBottom: '15px' }}>
              <label htmlFor='position' className='label-title'>재고관리 방식:</label>
              <InputBar inputId={'position'} id={'position'} placeholderMsg={'재고관리 방식'} />
            </div>
            <div className='modal-div' style={{ marginBottom: '15px' }}>
              <label htmlFor='position' className='label-title'>최대재고:</label>
              <InputBar inputId={'position'} id={'position'} placeholderMsg={'최대재고'} />
            </div>
          </div>
  
          <div className='modal-line'>
            <div className='modal-div' style={{ marginBottom: '15px' }}>
              <label htmlFor='name' className='label-title'>자재명:</label>
              <InputBar inputId={'name'} id={'name'} placeholderMsg={'자재명'} />
            </div>
            <div className='modal-div' style={{ marginBottom: '15px' }}>
              <label htmlFor='birth' className='label-title'>총재고량:</label>
              <InputBar inputId={'birth'} id={'birth'} placeholderMsg={'총재고량'} />
            </div>
            <div className='modal-div' style={{ marginBottom: '15px' }}>
              <label htmlFor='birth' className='label-title'>안전재고:</label>
              <InputBar inputId={'birth'} id={'birth'} placeholderMsg={'안전재고'} />
            </div>
            <div className='modal-div' style={{ marginBottom: '15px' }}>
              <label htmlFor='birth' className='label-title'>LeadTime:</label>
              <InputBar inputId={'birth'} id={'birth'} placeholderMsg={'LeadTime'} />
            </div>
          </div>
        </div>
      </form>
    </>
  );
};
export default StockNew;