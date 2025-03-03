import {defineConfig, loadEnv} from 'vite';
import react from '@vitejs/plugin-react';
import path from 'node:path';
import {fileURLToPath} from 'node:url';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

export default defineConfig(({command, mode}) => {
    // eslint-disable-next-line no-undef
    const env = loadEnv(mode, process.cwd());

    console.log(command);
    console.log(env);
    console.log(mode);

    return {
        plugins: [react()],
        define: {
            __APP_ENV__: JSON.stringify(env.VITE_APP_ENV),
            __API_URL__: JSON.stringify(env.VITE_BASE_URL_CLIENT)
        },
        resolve: {
            alias: {
                '@': path.resolve(__dirname, './src'),
                '@views': path.resolve(__dirname, './src/views'),
            },
        },
        build: {
            outDir: 'dist',
            sourcemap: true,
        },
        server: {
            port: Number(env.VITE_BASE_PORT_CLIENT),
            proxy: {
                '/api': {
                    target: env.VITE_PROXY_TARGET,
                    changeOrigin: true,
                    rewrite: (path) => path.replace(/^\/api/, ''),
                },
            },
        },
    };
});
