import React, { useEffect } from 'react';
import { GoogleOAuthProvider } from '@react-oauth/google';
import { Layout, theme } from 'antd';
import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import '../App.css';
import SideBar from './Common/SideBar';
import Login from './View/Login/Login';
import Notice from './View/Login/Notice';
import StockList from './View/Material/StockList';
import OrderList from './View/Order/OrderList';
import OrderNew from './View/Order/OrderNew';
import Bom from './View/Production/Bom';
import Mrp from './View/Production/Mrp';
import ProductManagement from './View/Production/ProductManagement';
import Status from './View/Production/Status';
import WorkPerfomance from './View/Production/WorkPerfomance';
import WorkPerfomanceNew from './View/Production/WorkPerfomanceNew';
import Perfomance from './View/Production/Perfomance';
import UserEdit from './View/User/UserEdit';
import UserList from './View/User/UserList';
import UserNew from './View/User/UserNew';
import MaterialOrderHistory from './View/Material/MaterialOrderHistory';
import Join from './View/User/Join';
import PerfomanceChart from './View/Production/PerfomanceChart';
import PerfomanceControllChart from './View/Production/PerfomanceControllChart';
import '../App.css'
import axios from 'axios';
const { Header, Content, Footer, Sider } = Layout;

function App() {
  const clientId = '362376129818-apm2mmh54l58gskanrm2hufa2emfdov2.apps.googleusercontent.com';
  const {
    token: { colorBgContainer, borderRadiusLG },
  } = theme.useToken();

  if (window.location.pathname === '/' || window.location.pathname === '/login' || window.location.pathname === '/users/join') {
    return (
      <GoogleOAuthProvider clientId={clientId}>
        <Router>
          <Routes>
            <Route path='/users/join' element={<Join />} />
            <Route path='/' element={<Login />} />
            <Route path='/notice' element={<Notice />} />
          </Routes>
        </Router>
      </GoogleOAuthProvider>
    )
    //return (<div><Notice /></div>)
  } else {
    return (
      <div className="App" style={{ position: 'relative' }}>
        <SideBar />
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
                <Route path='/orders' element={<OrderList />}>
                </Route>
                <Route path='/orders/new' element={<OrderNew />} />
                <Route path='/materials/stock' element={<StockList />} />
                <Route path='/materials/order-history' element={<MaterialOrderHistory />} />
                <Route path='/users/join' element={<Join />} />
                <Route path='/users' element={<UserList />} />
                <Route path='/users/new' element={<UserNew />} />
                <Route path='/users/edit/:empNo' element={<UserEdit />} />
                <Route path='/mrp' element={<Mrp />} />
                <Route path='/mrp/search' element={<Mrp />} />
                <Route path='/production-management' element={<ProductManagement />} />
                <Route path='/bom' element={<Bom />} />
                <Route path='/production-status' element={<Status />} />
                <Route path='/work-perfomance' element={<WorkPerfomance />} />
                <Route path='/production-perfomance' element={<Perfomance />} />
                <Route path='/production-perfomance/chart' element={<PerfomanceChart />} />
                <Route path='/production-perfomance/controll-chart' element={<PerfomanceControllChart />} />
                <Route path='/work-perfomance/new' element={<WorkPerfomanceNew />} />
              </Routes>
            </Router>
          </div>
        </Content>
      </div>
    );
  }
}

export default App;
