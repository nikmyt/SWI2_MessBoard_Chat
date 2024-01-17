import Image from 'next/image'
import styles from './page.module.css'

// Page.tsx

import React from 'react';
import ChatPage from '../pages/Chatpage';

function Page() {
  return (
      <div className="App">
        <ChatPage />
      </div>
  );
}

export default Page;