<template> 
    <ContentField_register>
        <div class="row">
            <div class="col-6">
                <img src="@/assets/image.svg" alt="" class="img-fluid" />
            </div>
            <div class="col-6">
                <form  @submit.prevent="register">
                    <h3>Welcome to Print System!</h3>
                    <div class="mb-3">
                        <div class="input-container">
                        <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
                            <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                            <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                        </svg> 
                        <input v-model="username" type="text" class="form-control" id="username" placeholder="Username">
                        </div>   
                    </div>
                    <div class="mb-3">
                        <div class="input-container">
                            <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-key" viewBox="0 0 16 16">
                                <path d="M0 8a4 4 0 0 1 7.465-2H14a.5.5 0 0 1 .354.146l1.5 1.5a.5.5 0 0 1 0 .708l-1.5 1.5a.5.5 0 0 1-.708 0L13 9.207l-.646.647a.5.5 0 0 1-.708 0L11 9.207l-.646.647a.5.5 0 0 1-.708 0L9 9.207l-.646.647A.5.5 0 0 1 8 10h-.535A4 4 0 0 1 0 8zm4-3a3 3 0 1 0 2.712 4.285A.5.5 0 0 1 7.163 9h.63l.853-.854a.5.5 0 0 1 .708 0l.646.647.646-.647a.5.5 0 0 1 .708 0l.646.647.646-.647a.5.5 0 0 1 .708 0l.646.647.793-.793-1-1h-6.63a.5.5 0 0 1-.451-.285A3 3 0 0 0 4 5z"/>
                                <path d="M4 8a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
                            </svg>
                        <input v-model="password" type="password" class="form-control" id="password" placeholder="Password">
                        </div>    
                    </div>
                    <div class="mb-3">
                        <div class="input-container">
                        <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-shield-lock" viewBox="0 0 16 16">
                            <path d="M5.338 1.59a61.44 61.44 0 0 0-2.837.856.481.481 0 0 0-.328.39c-.554 4.157.726 7.19 2.253 9.188a10.725 10.725 0 0 0 2.287 2.233c.346.244.652.42.893.533.12.057.218.095.293.118a.55.55 0 0 0 .101.025.615.615 0 0 0 .1-.025c.076-.023.174-.061.294-.118.24-.113.547-.29.893-.533a10.726 10.726 0 0 0 2.287-2.233c1.527-1.997 2.807-5.031 2.253-9.188a.48.48 0 0 0-.328-.39c-.651-.213-1.75-.56-2.837-.855C9.552 1.29 8.531 1.067 8 1.067c-.53 0-1.552.223-2.662.524zM5.072.56C6.157.265 7.31 0 8 0s1.843.265 2.928.56c1.11.3 2.229.655 2.887.87a1.54 1.54 0 0 1 1.044 1.262c.596 4.477-.787 7.795-2.465 9.99a11.775 11.775 0 0 1-2.517 2.453 7.159 7.159 0 0 1-1.048.625c-.28.132-.581.24-.829.24s-.548-.108-.829-.24a7.158 7.158 0 0 1-1.048-.625 11.777 11.777 0 0 1-2.517-2.453C1.928 10.487.545 7.169 1.141 2.692A1.54 1.54 0 0 1 2.185 1.43 62.456 62.456 0 0 1 5.072.56z"/>
                            <path d="M9.5 6.5a1.5 1.5 0 0 1-1 1.415l.385 1.99a.5.5 0 0 1-.491.595h-.788a.5.5 0 0 1-.49-.595l.384-1.99a1.5 1.5 0 1 1 2-1.415z"/>
                        </svg>
                        <input v-model="confirmedPassword" type="password" class="form-control" id="confirmedPassword" placeholder="ConfiremedPassword">
                        </div>    
                    </div>
                    <div class="error-message">{{ error_message }}</div>
                    <div class="btn-container">
                        <button type="submit" class="btn btn-primary">注册</button>
                    </div>
                </form>
                <p class="account">已有账号？<router-link :to="{name: 'loginview'}">去登录</router-link></p>
            </div>
        </div>
    </ContentField_register>
</template>

<script>
import ContentField_register from '@/components/ContentField_register.vue'
import { ref } from 'vue'
import router from '@/router/index';
import $ from 'jquery';
export default {
    components: {
        ContentField_register,
    },
    setup() {
        let username = ref('');
        let password = ref('');
        let confirmedPassword = ref('');
        let error_message = ref('');

        const register = () => {
            $.ajax({
                url: "http://127.0.0.1:4000/user/register/",
                type:"POST",
                data: {
                    username: username.value,
                    password: password.value,
                    confirmedPassword: confirmedPassword.value
                },
                success(resp) {
                    if(resp.error_message === "success") {
                        router.push({name: "loginview"});
                    } else {
                        error_message.value = resp.error_message;
                    }
                },
            });
        }

        return {
            username,
            password,
            confirmedPassword,
            error_message,
            register,
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
div.input-container {
    display: flex;
    align-items: center;
}
svg{
    margin-right: 10px;
}
.account {
    margin-top: 10px;
    margin-left: 30px;
}
.img-fluid {
    width: auto;     /* 设置宽度为 100% */
    height: auto;     /* 高度自动，保持纵横比 */

}
div.error-message {
    color: red;
}
</style>