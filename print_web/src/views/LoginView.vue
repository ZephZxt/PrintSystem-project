<template> 
    <ContentField_login v-if="!$store.state.user.pulling_info">
        <div class="row">
            <div class="col-6">
                <img src="@/assets/image.svg" alt="" class="img-fluid" />
            </div>
            <div class="col-6">
                <form  @submit.prevent="login">
                    <h3>Welcome to Print System!</h3>
                    <div class="mb-4">
                        <div class="input-container">
                        <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
                            <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                            <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                        </svg> 
                        <input v-model="username" type="text" class="form-control" id="username" placeholder="Username">
                        </div>
                        
                    </div>
                    <div class="mb-4">
                        <div class="input-container">
                            <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-key" viewBox="0 0 16 16">
                                <path d="M0 8a4 4 0 0 1 7.465-2H14a.5.5 0 0 1 .354.146l1.5 1.5a.5.5 0 0 1 0 .708l-1.5 1.5a.5.5 0 0 1-.708 0L13 9.207l-.646.647a.5.5 0 0 1-.708 0L11 9.207l-.646.647a.5.5 0 0 1-.708 0L9 9.207l-.646.647A.5.5 0 0 1 8 10h-.535A4 4 0 0 1 0 8zm4-3a3 3 0 1 0 2.712 4.285A.5.5 0 0 1 7.163 9h.63l.853-.854a.5.5 0 0 1 .708 0l.646.647.646-.647a.5.5 0 0 1 .708 0l.646.647.646-.647a.5.5 0 0 1 .708 0l.646.647.793-.793-1-1h-6.63a.5.5 0 0 1-.451-.285A3 3 0 0 0 4 5z"/>
                                <path d="M4 8a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
                            </svg>
                        <input v-model="password" type="password" class="form-control" id="password" placeholder="Password">
                        </div>
                        
                    </div>
                    <div class="error-message">{{ error_message }}</div>
                    <div class="btn-container">
                        <button type="submit" class="btn btn-primary">登录</button>
                    </div>
                </form>
                <p class="account">还没有账号？<router-link :to="{name: 'registerview'}">去注册</router-link></p>
            </div>
        </div>
    </ContentField_login>
</template>

<script>
import ContentField_login from '@/components/ContentField_login.vue'
import { ref } from 'vue'
import { useStore } from 'vuex';
import router from '@/router/index';
export default {
    components: {
        ContentField_login,
    },
    setup() {
        const store = useStore();
        let username = ref('');
        let password = ref('');
        let error_message = ref('');

        const jwt_token = localStorage.getItem("jwt_token");
        if(jwt_token) {
            store.commit("updateToken", jwt_token);
            store.dispatch("getInfo", {
                success() {
                    if(store.state.user.role === "Publisher") {
                        router.push({name: "Home"});
                        store.commit("updatePullingInfo", false);
                    }else {
                        router.push({name: "docs_a"});
                        store.commit("updatePullingInfo", false);
                    }
                    
                },
                error() {
                    store.commit("updatePullingInfo", false);
                    
                }
            });
        } else {
            store.commit("updatePullingInfo", false);
        }

        const login = () => {
            error_message.value = "";
            store.dispatch("login",{
                username: username.value,
                password: password.value,
            success() {
                store.dispatch("getInfo", {
                    success() {
                        if(store.state.user.role === "Publisher") {
                            router.push({name: "Home"});
                        } else {
                            router.push({name: "docs_a"});
                        }
                    }
                });
            },
            error() {
                error_message.value = "用户名或密码错误";
            }   
        })
        }
        return {
            username,
            password,
            error_message,
            login
        }
    }
}
</script>
<style scoped>
button {
    width: 80%;
    border-radius: 25px;    
    padding: 10px 20px;     
    background-color: #007bff; 
    color: white;            
    border: none;            
    cursor: pointer;         
    font-size: 16px;         
    transition: background-color 0.3s; 
}
.btn-container {
    display: flex;          /* 使用 Flexbox */
    justify-content: center; /* 水平居中 */
}
div.error-message {
    color: red;
}
div.input-container {
    display: flex;
    align-items: center;
}
svg{
    margin-right: 10px;
}
input {
    padding: 8px;
    border-radius: 5px;
    border: 1px solid #ccc;
}
.img-fluid {
    width: 100%;      /* 设置宽度为 100% */
    height: auto;     /* 高度自动，保持纵横比 */
}
.account {
    margin-top: 25px;
    margin-left: 30px;
}

</style>