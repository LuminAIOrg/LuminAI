# proxy to kubectl
``kubectl proxy``
when multiple contexts are defined in ./.kube/config
set the default context
``kubectl config use-context student@leocloud``

# kubectl get pods
``kubectl get pods``

# connect to postgres
get the pod name
``kubectl get pods``
``kubectl port-forward <pod-name> 6543:5432``



### Apply deployment
``kubectl apply -f ./k8s/luminai-deployment.yml``
### Delete deployment
``kubectl delete -n <namespace> deployment luminai``
