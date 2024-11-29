<template>
    <div class="background">
        <NavBar_P/>
        <div class="card">
            <div class="card-header">
                <span class="card-title">我的印刷单据</span>
                <button type="button" class="btn btn-primary float-end" data-bs-toggle="modal" data-bs-target="#add-doc-modal">
                    创建单据
                </button>
                <!-- Modal -->
                <div class="modal fade" id="add-doc-modal" tabindex="-1">
                    <div class="modal-dialog">
                        <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5">创建印刷单据</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="mb-3">
                            <label for="add-doc-pname" class="form-label">出版商名称</label>
                            <input v-model="docadd.pname" type="text" class="form-control" id="add-doc-pname">
                            </div>
                            <div class="mb-3">
                            <label for="add-doc-bname" class="form-label">书籍名称</label>
                            <input v-model="docadd.bname" type="text" class="form-control" id="add-doc-bname">
                            </div>
                            <div class="mb-3">
                            <label for="add-doc-font" class="form-label">印刷字体</label>
                            <input v-model="docadd.font" type="text" class="form-control" id="add-doc-font">
                            </div>
                            <div class="mb-3">
                            <label for="add-doc-mname" class="form-label">印刷材料名称</label>
                            <input v-model="docadd.mname" type="text" class="form-control" id="add-doc-mname">
                            </div>
                            <div class="mb-3">
                            <label for="add-doc-num" class="form-label">印刷数量</label>
                            <input v-model="docadd.num" type="text" class="form-control" id="add-doc-num">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <div class="error_message">{{ docadd.error_message }}</div>
                            <button type="button" class="btn btn-primary" @click="add_doc">提交</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                        </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <div class="table-container">
                    <table class="table table-hover">
                <thead>
                    <tr>
                        <th>单据编号</th>
                        <th>出版商编号</th>
                        <th>出版商名称</th>
                        <th>书籍名称</th>
                        <th>印刷字体</th>
                        <th>印刷数量</th>
                        <th>创建时间</th>
                        <th>修改时间</th>
                        <th>是否被加入印刷任务</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="doc in docs" :key="doc.dno">
                        <td>{{ doc.dno }}</td>
                        <td>{{ doc.pno }}</td>
                        <td>{{ doc.pname }}</td>
                        <td>{{ doc.bname }}</td>
                        <td>{{ doc.font }}</td>
                        <td>{{ doc.num }}</td>
                        <td>{{ doc.createtime }}</td>
                        <td>{{ doc.modifytime }}</td>
                        <td>{{ doc.state }}</td>
                        <td>
                            <button type="button" class="btn btn-secondary" style="margin-right: 10px;" data-bs-toggle="modal" :data-bs-target="'#update-doc-modal-' + doc.dno">
                                修改
                            </button>
                            <button type="button" class="btn btn-danger" @click="remove_doc(doc)">
                                删除
                            </button>

                            <!-- Modal -->
                            <div class="modal fade" :id="'update-doc-modal-' + doc.dno" tabindex="-1">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5">修改印刷单据</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="mb-3">
                                        <label for="add-doc-pname" class="form-label">出版商名称</label>
                                        <input v-model="doc.pname" type="text" class="form-control" id="add-doc-pname">
                                        </div>
                                        <div class="mb-3">
                                        <label for="add-doc-bname" class="form-label">书籍名称</label>
                                        <input v-model="doc.bname" type="text" class="form-control" id="add-doc-bname">
                                        </div>
                                        <div class="mb-3">
                                        <label for="add-doc-font" class="form-label">印刷字体</label>
                                        <input v-model="doc.font" type="text" class="form-control" id="add-doc-font">
                                        </div>
                                        <div class="mb-3">
                                        <label for="add-doc-mname" class="form-label">印刷材料名称</label>
                                        <input v-model="doc.mname" type="text" class="form-control" id="add-doc-mname">
                                        </div>
                                        <div class="mb-3">
                                        <label for="add-doc-num" class="form-label">印刷数量</label>
                                        <input v-model="doc.num" type="text" class="form-control" id="add-doc-num">
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <div class="error_message">{{ doc.error_message }}</div>
                                        <button type="button" class="btn btn-primary" @click="update_doc(doc)">保存修改</button>
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                                    </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                </tbody>
                </table>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import $ from "jquery";
import NavBar_P from "@/components/NavBar_P.vue";
import { useStore } from "vuex";
import { ref,reactive } from "vue";
import { Modal } from "bootstrap/dist//js/bootstrap";
export default{
    components: {
        NavBar_P
    },
    setup() {
        const store = useStore();
        let docs = ref([]);

        const docadd = reactive({
            pname: "",
            bname: "",
            font: "",
            mname: "",
            num: "",
            error_message: "",
        });
        
        const refresh_docs = () => {
            $.ajax({
                url: "http://127.0.0.1:4000/docs/get/",
                type: "GET",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,  
                },
                success(resp) {
                    docs.value = resp;
                }
            })
        }
        refresh_docs();

        const add_doc = () => {
            docadd.error_message = "";
            $.ajax({
                url: "http://127.0.0.1:4000/docs/add/",
                type: "POST",
                data: {
                    pName: docadd.pname,
                    bName: docadd.bname,
                    font: docadd.font,
                    mName: docadd.mname,
                    num: docadd.num,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,  
                },
                success(resp) {
                    if(resp.error_message === "success") {
                        docadd.pname = "";
                        docadd.bname = "";
                        docadd.font = "";
                        docadd.mname = "";
                        docadd.num = "";
                        Modal.getInstance(document.querySelector('#add-doc-modal')).hide();
                        const backdrop = document.querySelector('.modal-backdrop');
                        if (backdrop) {
                            backdrop.remove(); // 移除模态框的遮罩层
                        }
                        location.reload();
                        refresh_docs();
                    } else {
                        docadd.error_message = resp.error_message;
                    }
                }
            })
        }

        const update_doc = (doc) => {
            docadd.error_message = "";
            $.ajax({
                url: "http://127.0.0.1:4000/docs/update/",
                type: "POST",
                data: {
                    doc_no: doc.dno,
                    pName: doc.pname,
                    bName: doc.bname,
                    font: doc.font,
                    mName: doc.mname,
                    num: doc.num,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,  
                },
                success(resp) {
                    if(resp.error_message === "success") {
                        Modal.getInstance(document.querySelector('#update-doc-modal-' + doc.dno)).hide();
                        const backdrop = document.querySelector('.modal-backdrop');
                        if (backdrop) {
                            backdrop.remove(); // 移除模态框的遮罩层
                        }
                        location.reload();
                        refresh_docs();
                    } else {
                        console.log(resp);
                        console.log(doc.dno);
                        docadd.error_message = resp.error_message;
                    }
                }
            })
        }

        const remove_doc = (doc) => {
            if (confirm("确认删除该单据吗？")) { 
            $.ajax({
                url: "http://127.0.0.1:4000/docs/remove/",
                type: "POST",
                data: {
                    doc_no: doc.dno,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,  
                },
                success(resp) {
                    console.log(resp);
                    if(resp.error_message === "success") {
                        refresh_docs();
                    }
                }
            })
        }
            else {
                console.log("用户取消了删除操作");
            }

    }

    return {
            docs,
            docadd,
            add_doc,
            remove_doc,
            update_doc,
        }
}
}
</script>

<style scoped>
.background {
    background-image: url("@/assets/绿色护眼.png");
    background-size: cover; /* 背景图填满整个容器 */
    background-repeat: no-repeat; /* 不重复背景图 */
    background-position: center; /* 背景图居中 */
    height: 100vh; /* 高度设置为视口高度 */
}
div.card {
    margin-top: 20px;
    margin-left: 90px;
    margin-right: 90px;
    
}
.card-title {
    font-size: 120%;
}
div.error_message {
    color: red;
}

.table-container {
    max-height: 500px; /* 可根据需要调整 */
    overflow-y: auto;  /* 启用垂直滚动 */
    
}

.table thead th {
    position: sticky; /* 使表头粘性 */
    top: 0;          /* 固定在顶部 */
    background-color: #f8f9fa; /* 背景色，与Bootstrap一致 */
    z-index: 2;     /* 确保在内容上方 */
}

</style>