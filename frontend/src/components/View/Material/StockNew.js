import React from 'react';
import { InputBar } from '../../Common/Module';

const StockNew = ({ formAction }) => {
  
  return (
    <>
      <form id='stockNewForm' method='post' action={formAction} style={{ borderTop: '1px solid #ccc' }}>
        <div style={{display: 'flex', flexDirection: 'row'}}>
          <div  className='modal-line' style={{ marginRight: '40px' }}>
            <div className='modal-div' >
              <label htmlFor='materialId' className='label-title'>자재코드:</label>
              <InputBar inputId={'materialId'} id={'materialId'} placeholderMsg={'자재코드'} />
            </div>
            <div className='modal-div' style={{ marginBottom: '15px', display: 'flex', alignItems: 'center' }}>
              <label htmlFor='unit' className='label-title'>단위:</label>
              <InputBar inputId={'unit'} id={'unit'} placeholderMsg={'단위'} />
            </div>
            <div className='modal-div' style={{ marginBottom: '15px' }}>
              <label htmlFor='methodId' className='label-title'>재고관리 방식:</label>
              <InputBar inputId={'methodId'} id={'methodId'} placeholderMsg={'재고관리 방식'} />
            </div>
            <div className='modal-div' style={{ marginBottom: '15px' }}>
              <label htmlFor='maxStock' className='label-title'>최대재고:</label>
              <InputBar inputId={'maxStock'} id={'maxStock'} placeholderMsg={'최대재고'} />
            </div>
          </div>
  
          <div className='modal-line'>
            <div className='modal-div' style={{ marginBottom: '15px' }}>
              <label htmlFor='materialName' className='label-title'>자재명:</label>
              <InputBar inputId={'materialName'} id={'materialName'} placeholderMsg={'자재명'} />
            </div>
            <div className='modal-div' style={{ marginBottom: '15px' }}>
              <label htmlFor='totalStock' className='label-title'>총재고량:</label>
              <InputBar inputId={'totalStock'} id={'totalStock'} placeholderMsg={'총재고량'} />
            </div>
            <div className='modal-div' style={{ marginBottom: '15px' }}>
              <label htmlFor='safetyStock' className='label-title'>안전재고:</label>
              <InputBar inputId={'safetyStock'} id={'safetyStock'} placeholderMsg={'안전재고'} />
            </div>
            <div className='modal-div' style={{ marginBottom: '15px' }}>
              <label htmlFor='LeadTime' className='label-title'>LeadTime:</label>
              <InputBar inputId={'LeadTime'} id={'LeadTime'} placeholderMsg={'LeadTime'} />
            </div>
          </div>
        </div>
      </form>
    </>
  );
};
export default StockNew;