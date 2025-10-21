// src/container/PageContainer.jsx

import React from 'react';
// Material UI import'unu sil: import { Container } from '@mui/material'
// React Bootstrap import'unu ekle:
import { Container } from 'react-bootstrap';

function PageContainer({ children }) {
    // İçindeki gereksiz <div>'i kaldır
    // 'disableGutters' ve 'maxWidth' MUI'a özeldir, React Bootstrap'te 'fluid' kullanılır.
    return (
        <Container fluid> {/* 'fluid', container'ın tam genişlikte olmasını sağlar */}
            {children}
        </Container>
    );
}

export default PageContainer;