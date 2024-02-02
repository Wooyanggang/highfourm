import React from 'react';
import { Layout, Menu, theme, Breadcrumb } from 'antd';
const { Header, Content, Footer, Sider } = Layout;

const PageTitle = ({ value }) => {
  const {
    token: { colorBgContainer, borderRadiusLG },
  } = theme.useToken();
  return (
    <Header
      style={{
        display: 'flex',
        alignItems: 'center',
        padding: '0 0 0 32px',
        margin: 0,
        width: 'calc(100% - 300px)',
        height: '120px',
        verticalAlign: 'center',
        background: colorBgContainer,
        backgroundColor: '#fff',
        borderBottom: '1px solid #ccc',
        position: 'absolute',
        top: 0,
        right: 0,
        boxSizing: 'border-box',
        zIndex: '99',
      }}
    >
      <h2>{value}</h2>
    </Header>
  );
}

export default PageTitle;