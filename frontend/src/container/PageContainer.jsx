import { Container } from '@mui/material'
import React from 'react'

function PageContainer({ children }) {
    return (
        <div>
            <Container disableGutters maxWidth={false}>
                {children}
            </Container>
        </div>
    )
}

export default PageContainer
