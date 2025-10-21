import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
    plugins: [react()],

    // ----- BU BÖLÜMÜ EKLEYİN -----
    server: {
        proxy: {
            // '/api' ile başlayan tüm istekleri yakala
            '/api': {
                target: 'http://localhost:8080', // Backend sunucunuzun adresi
                changeOrigin: true, // CORS hatalarını önlemek için
                rewrite: (path) => path.replace(/^\/api/, '') // '/api' kısmını silerek isteği gönder
            }
        }
    }
    // ------------------------------
})