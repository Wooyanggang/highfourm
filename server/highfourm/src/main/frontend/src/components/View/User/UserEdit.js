import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { BtnBlue, BtnWhite, InputBar } from '../../Common/Module';
import PageTitle from '../../Common/PageTitle';
import axios from 'axios';

const UserNew = () => {
  const [htmlContent, setHtmlContent] = useState('');
  const { empNo } = useParams();

  useEffect(() => {
    fetch(`http://localhost:8080/users/edit/${empNo}`)
      .then(response => response.text())
      .then(data => setHtmlContent(data));
  }, []);

  const navigate = useNavigate();
  const [userInfo, setUserInfo] = useState([]);

  const goBackNavigate = () => {
    navigate(-1)
  }

  useEffect(() => {
    async function fetchData() {
      try {
        const res = await axios.get('/users/edit/:userNo');

        // const userData = await res.data
        //   key: rowData.userNo,
        //   user_name: rowData.userName,
        //   emp_no: rowData.empNo,
        //   birth: rowData.birth,
        //   position: rowData.position,
        //   email: rowData.email,
        //   register_state: rowData.registerState,

        setUserInfo(res.data);
        console.log(res.data);
      } catch (e) {
        console.error(e.message);
      }
    }
    fetchData();
  }, []);

  return (
    <div>
      {/* <div dangerouslySetInnerHTML={{ __html: htmlContent }}></div> */}
      <PageTitle value={'사용자 수정'} />
      <form method='post' action='/users/edit'>
        <div className='flex-line'>
          <div className='flex-div'>
            <label htmlFor='emp_no' className='label-title'>사번</label>
            <InputBar id={'emp_no'} name={'emp_no'} value={userInfo.empNo} disabled={true} />
          </div>
          <div className='flex-div'>
            <label htmlFor='user_name' className='label-title'>이름</label>
            <InputBar id={'user_name'} name={'user_name'} value={'홍길동'} />
          </div>
        </div>
        <div className='flex-line'>
          <div className='flex-div'>
            <label htmlFor='position' className='label-title'>직급</label>
            <InputBar id={'position'} name={'position'} value={'사원'} />
          </div>
          <div className='flex-div'>
            <label htmlFor='birth' className='label-title'>생년월일</label>
            <InputBar id={'birth'} name={'birth'} value={'1990-01-01'} />
          </div>
        </div>
        <div className='flex-div' >
          <label htmlFor='email' className='label-title'>이메일 주소</label>
          <InputBar id={'email'} name={'email'} value={'hong123'} disabled={true} />
          <span className='email'>@gmail.com</span>
        </div>
        <p style={{ color: 'rgba(0, 0, 0, 0.45)', marginBottom: '40px' }}>사용자 이메일은 수정 불가합니다. 수정을 원할 시 정보를 삭제하고 재등록 해주세요.</p>
        <div className='flex-btn'>
          <BtnBlue type='submit' value={'수정하기'} />
          <BtnWhite value={'취소'} onClick={goBackNavigate} />
        </div>
      </form>
    </div>
  )
}

export default UserNew
