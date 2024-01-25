import React from 'react';
import { GoogleOAuthProvider } from '@react-oauth/google';
import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import { Layout, Menu, theme, Breadcrumb } from 'antd';
import SideBar from './Common/SideBar';
import Login from './View/Login/Login';
import Notice from './View/Login/Notice';
import Container from './Common/Container';
import OrderList from './View/Order/OrderList'
import ProductManagement from './View/Production/ProductManagement';
import Mrp from './View/Production/Mrp';
import UserNew from './View/User/UserNew';
import UserList from './View/User/UserList';
import UserEdit from './View/User/UserEdit';
import Bom from './View/Production/Bom';
import WorkPerfomance from './View/Production/WorkPerfomance';
import OrderNew from './View/Order/OrderNew';
import '../App.css'
const { Header, Content, Footer, Sider } = Layout;

function App() {
  const clientId = '362376129818-apm2mmh54l58gskanrm2hufa2emfdov2.apps.googleusercontent.com';
  const {
    token: { colorBgContainer, borderRadiusLG },
  } = theme.useToken();

  if (window.location.pathname === '/login') {
    return (
      <GoogleOAuthProvider clientId={clientId}>
        <Login />
      </GoogleOAuthProvider>
    )
    return (<div><Notice /></div>)
  } else {
    return (
      <div className="App" style={{ position: 'relative' }}>
        <SideBar />
        <Container />
        <Content
          style={{
            padding: '152px 32px 32px 332px',
            background: colorBgContainer,
            borderRadius: borderRadiusLG,
            boxSizing: 'border-box',
          }}
        >
          <div>
            <Router>
              <Routes>
                <Route path='/orders' element={<OrderList />} />
                <Route path='/orders/new' element={<OrderNew />} />
                <Route path='/users' element={<UserList />} />
                <Route path='/users/new' element={<UserNew />} />
                <Route path='/users/edit' element={<UserEdit />} />
                <Route path='/mrp' element={<Mrp />} />
                <Route path='/product-management' element={<ProductManagement />} />
                <Route path='/bom' element={<Bom />} />
                <Route path='/work-perfomance' element={<WorkPerfomance />} />
              </Routes>
            </Router>
          </div>
        </Content>
      </div>
    );
  }
}

export default App;
