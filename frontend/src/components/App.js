import React from 'react';
import { GoogleOAuthProvider } from '@react-oauth/google';
import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import { Layout, Menu, theme, Breadcrumb } from 'antd';
import SideBar from './Common/SideBar';
import Login from './View/Login/Login';
import Notice from './View/Login/Notice';
import Container from './Common/Container';
import OrderList from './View/Order/OrderList'
import OrderNew from './View/Order/OrderNew';
const { Header, Content, Footer, Sider } = Layout;

function App() {
  const clientId = '362376129818-apm2mmh54l58gskanrm2hufa2emfdov2.apps.googleusercontent.com';
  const {
    token: { colorBgContainer, borderRadiusLG },
  } = theme.useToken();

  if (window.location.pathname === '/') {
    return (
      <GoogleOAuthProvider clientId={clientId}>
        <Login />
      </GoogleOAuthProvider>
    )
    //return (<div><Notice /></div>)
  } else {
    return (
      <div className="App" style={{ position: 'relative' }}>
        <SideBar />
        <Container />
        <Content
          style={{
            padding: '152px 0 0 332px',
            background: colorBgContainer,
            borderRadius: borderRadiusLG,
            boxSizing: 'border-box',
          }}
        />
        <div>
          <Router>
            <Routes>
              <Route path='/orders' element={<OrderList />} />
              <Route path='/orders/new' element={<OrderNew />} />
            </Routes>
          </Router>
        </div>
      </div>
    );
  }
}
export default App;
