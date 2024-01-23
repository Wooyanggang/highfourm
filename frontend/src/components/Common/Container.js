import React from 'react';
import { Layout, Menu, theme, Breadcrumb } from 'antd';
const { Header, Content, Footer, Sider } = Layout;

const Container = () => {
  const {
    token: { colorBgContainer, borderRadiusLG },
  } = theme.useToken();
  return (
      <Header
        style={{
          padding: 0,
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
          zIndex: '99',
        }}
      >
        <h2 style={{ margin: '0 0 0 32px', fontSize: '24px', lineHeight: '120px' }}>제목{/* [[${title}]] */}</h2>
      </Header>
  );
}

export default Container;