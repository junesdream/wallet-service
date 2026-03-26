/* eslint-disable */
// @ts-nocheck
import React, { useState, useEffect, useCallback } from 'react';
import axios from 'axios';

function App() {
    const [wallet, setWallet] = useState<any>(null);
    const [history, setHistory] = useState<any[]>([]);
    const [amount, setAmount] = useState<string>('');

    // @ts-ignore
    const API_BASE_URL = "http://localhost:8080";
    const API_BASE = `${API_BASE_URL}/api/wallets/1`;

    const fetchData = useCallback(async () => {
        try {
            const walletRes = await axios.get(`${API_BASE_URL}/api/wallets`);
            setWallet(walletRes.data[0]);
            const historyRes = await axios.get(`${API_BASE}/history`);
            setHistory(historyRes.data);
        } catch (err) {
            console.error("] ERROR [", err);
        }
    }, [API_BASE_URL, API_BASE]);

    useEffect(() => {
        fetchData();
    }, [fetchData]);

    const handleAction = async (type: 'deposit' | 'withdraw') => {
        if (!amount) return;
        try {
            await axios.put(`${API_BASE}/${type}?amount=${amount}`);
            setAmount('');
            fetchData();
        } catch (err: any) {
            alert(`]] ERROR: ${err.response?.data?.error || 'Unknown'}`);
        }
    };

    if (!wallet) return <div className="matrix-container">] BOOTING SYSTEM... [</div>;

    return (
        <div className="matrix-container">
            <div className="matrix-border">
                {/* Header */}
                <div style={{display: 'flex', justifyContent: 'space-between', marginBottom: '20px'}}>
                    <h1 style={{fontSize: '1.5rem'}}>] WALLET.CORE.v25 [</h1>
                    <div style={{fontSize: '0.7rem', textAlign: 'right'}}>
                        <p>USER: {wallet.ownerName.toUpperCase()}</p>
                        <p style={{color: '#3f3'}}>STATUS: ONLINE</p>
                    </div>
                </div>

                {/* Balance Card */}
                <div className="matrix-card">
                    <p style={{fontSize: '0.8rem', color: '#3f3'}}>AVAILABLE CREDITS:</p>
                    <p style={{fontSize: '3rem', fontWeight: 'bold', margin: '10px 0'}}>
                        {wallet.balance.toFixed(2)} <span>{wallet.currency}</span>
                    </p>
                </div>

                {/* Action Area */}
                <div style={{marginBottom: '30px'}}>
                    <p style={{fontSize: '0.8rem'}}>$ EXECUTE_TRANSACTION:</p>
                    <input
                        type="number"
                        className="matrix-input"
                        placeholder="0.00"
                        value={amount}
                        onChange={(e) => setAmount(e.target.value)}
                    />
                    <div style={{display: 'flex', gap: '20px'}}>
                        <button onClick={() => handleAction('deposit')} className="btn-deposit">] DEPOSIT [</button>
                        <button onClick={() => handleAction('withdraw')} className="btn-withdraw">] WITHDRAW [</button>
                    </div>
                </div>

                {/* History Log */}
                <h2 style={{fontSize: '1rem'}}>] SYSTEM_ACTIVITY.LOG [</h2>
                <div className="log-container">
                    {history.map((t: any) => (
                        <div key={t.id} style={{display: 'flex', justifyContent: 'space-between', padding: '10px 0', borderBottom: '1px solid #020'}}>
                            <div>
                                <p style={{fontSize: '0.9rem', margin: 0}}>{t.type}</p>
                                <p style={{fontSize: '0.7rem', color: '#050', margin: 0}}>{new Date(t.timestamp).toLocaleString()}</p>
                            </div>
                            <p style={{fontWeight: 'bold', color: t.type === 'DEPOSIT' ? '#0f0' : '#f00'}}>
                                {t.type === 'DEPOSIT' ? '+' : '-'}{t.amount.toFixed(2)}
                            </p>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
}

export default App;