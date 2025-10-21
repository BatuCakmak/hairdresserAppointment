// src/App.jsx

import { useState } from 'react';
import './App.css';
import PageContainer from './container/PageContainer';
import RouterConfig from './config/RouterConfig';

function App() {
    // Gereksiz import'lar ve state'ler kaldırıldı.
    return (
        <PageContainer>
            <RouterConfig />
        </PageContainer>
    );
}

export default App;