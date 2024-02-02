import React from 'react';
import { Col, Layout, Row, } from 'antd';
import MainFooter from '../../Common/MainFooter';
import MainHeader from '../../Common/MainHeader';
import { BtnLogin, SignInputBar } from '../../Common/Module';

const { Content } = Layout;

const Login = () => {

  const onFinish = (values) => {
    console.log('Success:', values);
  };
  const onFinishFailed = (errorInfo) => {
    console.log('Failed:', errorInfo);
  };

  return (
    <Layout>
      <MainHeader />
      <Content>
        <Row justify="center" align="top" style={{ minHeight: '80vh' }}>
          <Col span={8}>
            <div style={{ textAlign: 'center', verticalAlign: 'center' }}>
              <div className='signin-text'>사용자 계정 생성</div>
              <form method='' action='' className='sign-in'>
                <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
                  <div className='signin-div'>
                    <label htmlFor='id' className='signin-title'>아이디*</label>
                    <SignInputBar placeholderMsg='아이디' inputId={'id'} id={'id'} required='아이디를 입력해주세요!' />
                  </div>
                  <div className='signin-div'>
                    <label htmlFor='id' className='signin-title'>비밀번호*</label>
                    <SignInputBar placeholderMsg='비밀번호' inputId={'id'} id={'id'} required='비밀번호를 입력해주세요!' />
                  </div>
                  <div className='signin-div'>
                    <label htmlFor='id' className='signin-title'>비밀번호 확인*</label>
                    <SignInputBar placeholderMsg='비밀번호 확인' inputId={'id'} id={'id'} required='비밀번호를 입력해주세요!' />
                  </div>
                  <div className='signin-div'>
                    <label htmlFor='id' className='signin-title'>사번*</label>
                    <SignInputBar placeholderMsg='사번' inputId={'id'} id={'id'} required='사번을 입력해주세요!' />
                  </div>
                  <div className='signin-div'>
                    <label htmlFor='id' className='signin-title'>이름*</label>
                    <SignInputBar placeholderMsg='이름' inputId={'id'} id={'id'} required='이름을 입력해주세요!' />
                  </div>
                  <div className='signin-div'>
                    <label htmlFor='password' className='signin-title'>생년월일*</label>
                    <SignInputBar type="password" placeholderMsg='생년월일' inputId={'password'} id={'password'} required='생년월일을 입력해주세요!' />
                  </div>
                  <div style={{ width: '330px' }}>
                    <BtnLogin type='submit' value={'계정 생성'} />
                  </div>
                </div>
              </form>
            </div>
          </Col>
        </Row>
      </Content>
      <MainFooter />
    </Layout>
  );
};

export default Login;